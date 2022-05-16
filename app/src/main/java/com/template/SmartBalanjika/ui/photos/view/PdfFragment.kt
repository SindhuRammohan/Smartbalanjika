package com.template.SmartBalanjika.ui.photos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.SmartBalanjika.data.model.pdf
import com.template.SmartBalanjika.databinding.PdffragmentMainBinding
import com.template.SmartBalanjika.ui.photos.adapter.pdfAdapter
import com.template.SmartBalanjika.ui.photos.viewmodel.PdfViewModel
import com.template.SmartBalanjika.utils.EmptyDataObserver
import com.template.SmartBalanjika.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.pdffragment_main.view.*

@AndroidEntryPoint
class PdfFragment : Fragment() , SearchView.OnQueryTextListener{
    private var _binding: PdffragmentMainBinding? = null
    private val binding get() = _binding!!
    private val photosViewModel: PdfViewModel by viewModels()
    private lateinit var pdfAdapter: pdfAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PdffragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        setUpViews()
        doObserveWork()
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        return view
    }


    private fun setUpViews() {

        pdfAdapter = pdfAdapter()
        setHasOptionsMenu(true)
        binding.apply {
            recyclerViewpdf.apply {
                searchLayoutbook.searchView.setOnQueryTextListener(this@PdfFragment)
                recyclerViewpdf.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,false)
                recyclerViewpdf.adapter = pdfAdapter

                pdfAdapter.onItemClick = { contact ->
                    val intent = Intent(activity, WebViewActivity::class.java)
                    intent.putExtra("Username",contact.link)
                    startActivity(intent)
                    // do something with your item
                    Log.d("TAG", contact.date + contact.link)
                }


                val emptyDataObserver = EmptyDataObserver(recyclerViewpdf, empty_data_parentpdf)
                pdfAdapter.registerAdapterDataObserver(emptyDataObserver)
            }
        }

    }

    private fun doObserveWork() {
        photosViewModel.progressBarVisibility.observe(viewLifecycleOwner, Observer {
        })

        photosViewModel.getPhotosFeed().observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Status.SUCCESS -> {


                    Log.d("TAG", it.data.toString())
                    renderPhotosList(it.data!!)


                }

                Status.ERROR -> {


                    Log.d("TAG", "ERROR")
                }

                Status.LOADING -> {

                    Log.d("TAG", "LOADING")

                }


            }

        })



    }

    private fun renderPhotosList(photosList: List<pdf>) {

        Log.d("TAG", photosList.toString())
        pdfAdapter.addData(photosList)
        pdfAdapter.notifyDataSetChanged()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        pdfAdapter.filter.filter(query)
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {

        pdfAdapter.filter.filter(newText)

        return false

    }


}