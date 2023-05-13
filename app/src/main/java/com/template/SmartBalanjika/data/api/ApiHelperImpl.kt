package com.template.SmartBalanjika.data.api

import com.template.SmartBalanjika.data.model.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPhotos(): Response<List<Photos>>  = apiService.getPhotosUrl()
    override suspend fun getPdf(): Response<List<pdf>>  = apiService.getPdfsUrl()
    override suspend fun getSangam(): Response<List<Sangam>>  = apiService.getSangamList()
}