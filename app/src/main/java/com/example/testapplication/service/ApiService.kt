package com.example.testapplication.service

import com.example.testapplication.domain.PhotoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getPhotoList(
        @Query("key") key : String,
        @Query("q") q : String,
        @Query("image_type") imageType : String
    ) : Call<PhotoListResponse>

}