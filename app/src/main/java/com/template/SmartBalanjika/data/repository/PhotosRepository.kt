package com.template.SmartBalanjika.data.repository

import com.template.SmartBalanjika.data.api.ApiHelper
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {


    suspend fun photosFeed() = apiHelper.getPhotos()

    suspend fun pdfsFeed() = apiHelper.getPdf()
    suspend fun sangamsFeed() = apiHelper.getSangam()
}