package com.template.SmartBalanjika.ui.photos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.template.SmartBalanjika.data.model.pdf
import com.template.SmartBalanjika.data.repository.PhotosRepository
import com.template.SmartBalanjika.utils.NetworkHelper
import com.template.SmartBalanjika.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val photosRepository: PhotosRepository

) : ViewModel() {


    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData()
    var onResponse: MutableLiveData<Resource<List<pdf>>> = MutableLiveData()





    fun getPhotosFeed(): LiveData<Resource<List<pdf>>>{
        return onResponse
    }

    init {
        doPhotosWork();
    }

    private fun doPhotosWork() {


        viewModelScope.launch {


            if (networkHelper.isNetworkConnected()){

                photosRepository.pdfsFeed().let {

                    if (it.isSuccessful){
                        Log.d("TAG", "ERROR isSuccessful")
                        onResponse.postValue(Resource.success(it.body()))
//                        preferencesHelper.setHomeFeeds(it.body())
                        progressBarVisibility.postValue(false)
                    }else{
                        Log.d("TAG", "ERROR else")
                        onResponse.postValue(Resource.error(it.errorBody().toString(),null))
                        progressBarVisibility.postValue(false)
                    }
                }
            }
            else {
                onResponse.postValue(Resource.network("network",null))
            }

        }

    }


}