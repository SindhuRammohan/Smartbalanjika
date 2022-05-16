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

    @Headers("Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
//    @GET(ApiEndPoints.PHOTOS_URL)
    //for testing
    //https://mocki.io/fake-json-api
    //change with correct API
    @GET("https://mocki.io/v1/6e0b596c-42b7-4bda-a4e7-2987e46ae337")
    suspend fun getPhotosUrl(): Response<List<Photos>>

    @Headers("Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
//    @GET(ApiEndPoints.PDF_URL)
    @GET("https://mocki.io/v1/a4ba1a44-588d-4e12-9702-5a508fbac72b")
    suspend fun getPdfsUrl(): Response<List<pdf>>

    @Headers("Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    @GET(ApiEndPoints.PDF_URL)
    suspend fun getSangamList(): Response<List<Sangam>>
}