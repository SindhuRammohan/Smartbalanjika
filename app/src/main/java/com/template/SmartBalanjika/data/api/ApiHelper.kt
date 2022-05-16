package com.template.SmartBalanjika.data.api

import com.template.SmartBalanjika.data.model.*
import retrofit2.Response

interface ApiHelper {
    suspend fun getPhotos(): Response<List<Photos>>
    suspend fun getPdf(): Response<List<pdf>>
    suspend fun getSangam(): Response<List<Sangam>>
}