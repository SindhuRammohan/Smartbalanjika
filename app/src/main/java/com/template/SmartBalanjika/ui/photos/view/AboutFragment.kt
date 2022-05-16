package com.template.SmartBalanjika.ui.photos.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.SmartBalanjika.ui.photos.adapter.SangamAdapter
import com.template.SmartBalanjika.ui.photos.viewmodel.SangamViewModel
import com.template.SmartBalanjika.utils.EmptyDataObserver
import com.template.SmartBalanjika.utils.Status
import androidx.lifecycle.Observer
import com.template.SmartBalanjika.data.model.Sangam
import com.template.SmartBalanjika.databinding.AboutUsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.pdffragment_main.view.*
@AndroidEntryPoint
class AboutFragment : Fragment() {
    private var _binding: AboutUsBinding? = null
    private val binding get() = _binding!!
    private val photosViewModel: SangamViewModel by viewModels()
    private lateinit var pdfAdapter: SangamAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AboutUsBinding.inflate(inflater, container, false)
        val view = binding.root
        setUpViews()
        doObserveWork()
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        return view
    }


    private fun setUpViews() {

        pdfAdapter = SangamAdapter()
        setHasOptionsMenu(true)
        binding.apply {
            sangamlist.apply {
                sangamlist.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,false)
                sangamlist.adapter = pdfAdapter

                pdfAdapter.onItemClick = { contact ->

                    // do something with your item
                    Log.d("TAG", contact.author)
                }


                val emptyDataObserver = EmptyDataObserver(sangamlist, empty_data_parentpdf)
                pdfAdapter.registerAdapterDataObserver(emptyDataObserver)
            }
        }

    }

    private fun doObserveWork() {
        photosViewModel.progressBarVisibility.observe(viewLifecycleOwner, Observer {
        })

        photosViewModel.getsangamsFeed().observe(viewLifecycleOwner, Observer {

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

    private fun renderPhotosList(photosList: List<Sangam>) {

        Log.d("TAG", photosList.toString())
        pdfAdapter.addData(photosList)
        pdfAdapter.notifyDataSetChanged()

    }




}