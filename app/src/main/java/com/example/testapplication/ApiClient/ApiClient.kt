package com.example.testapplication.ApiClient

import com.example.testapplication.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getApiClient() : ApiService {
        var retrofit = Retrofit.Builder().baseUrl("https://pixabay.com").
        addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiService::class.java)
    }
}