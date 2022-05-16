package com.template.SmartBalanjika.ui.photos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.SmartBalanjika.data.model.Photos
import com.template.SmartBalanjika.databinding.ActivityPhotosBinding
import com.template.SmartBalanjika.ui.photos.adapter.PhotosAdapter
import com.template.SmartBalanjika.ui.photos.viewmodel.PhotosViewModel
import com.template.SmartBalanjika.utils.EmptyDataObserver
import com.template.SmartBalanjika.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_photos.*

@AndroidEntryPoint
class PhotosFragment : Fragment() , SearchView.OnQueryTextListener{

    private val photosViewModel: PhotosViewModel by viewModels()
    private var _binding: ActivityPhotosBinding? = null
    private val binding get() = _binding!!
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityPhotosBinding.inflate(inflater, container, false)
        val view = binding.root
        setUpViews()
        doObserveWork()
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        return view
    }


    private fun setUpViews() {

        photosAdapter = PhotosAdapter()
        setHasOptionsMenu(true)
        binding.apply {
            recyclerView.apply {
                searchLayout.searchView.setOnQueryTextListener(this@PhotosFragment)
                recyclerView.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter = photosAdapter


                photosAdapter.onItemClick = { contact ->
                    val intent = Intent(activity, ContactDetailsActivity::class.java)
                    intent.putExtra("password",contact.author)
                    startActivity(intent)
                    // do something with your item
                    Log.d("TAG", contact.author)
                }



                val emptyDataObserver = EmptyDataObserver(recyclerView, empty_data_parent)
                photosAdapter.registerAdapterDataObserver(emptyDataObserver)
            }
        }

    }

    private fun doObserveWork() {
        photosViewModel.progressBarVisibility.observe(viewLifecycleOwner, Observer {
        })

        photosViewModel.getPhotosFeed().observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Status.SUCCESS -> {

                    Log.e("Sindhu TAG", "my Message" + it.data)


                    renderPhotosList(it.data!!)


                }

                Status.ERROR -> {


                }

                Status.LOADING -> {


                }


            }

        })



    }

    private fun renderPhotosList(photosList: List<Photos>) {

        photosAdapter.addData(photosList)
        photosAdapter.notifyDataSetChanged()

    }


    override fun onQueryTextSubmit(query: String?): Boolean {

        photosAdapter.filter.filter(query)
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {

        photosAdapter.filter.filter(newText)

        return false

    }

}