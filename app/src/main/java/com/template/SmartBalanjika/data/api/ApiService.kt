package com.template.SmartBalanjika.data.api

import com.template.SmartBalanjika.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(ApiEndPoints.LOGIN)
    @FormUrlEncoded
    suspend fun userLogin(
        @Field("email") userEmail: String,
        @Field("password") userPassword: String
    ): Response<User>

    @GET(ApiEndPoints.HOME)
    suspend fun homeFeeds(): Response<Home>

//    @GET(ApiEndPoints.PHOTOS_URL)
    //change with correct API
    @GET("https://raw.githubusercontent.com/SindhuRammohan/Smartbalanjika/main/README.md")
    suspend fun getPhotosUrl(): Response<List<Photos>>

//    @GET(ApiEndPoints.PDF_URL)
    @GET("https://raw.githubusercontent.com/SindhuRammohan/Smartbalanjika/main/README.md")
    suspend fun getPdfsUrl(): Response<List<pdf>>

    @GET("https://raw.githubusercontent.com/SindhuRammohan/Smartbalanjika/main/README.md")
    suspend fun getSangamList(): Response<List<Sangam>>
}