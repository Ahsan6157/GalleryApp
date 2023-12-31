package com.example.galleryapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetfofitHelper {
    private const val  BASE_URL = "https://pixabay.com/"
    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}