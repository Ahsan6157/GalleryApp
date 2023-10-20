package com.example.galleryapp.network

import com.example.galleryapp.model.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("/api/")
    suspend fun getImages(@Query("key") key:String,@Query("category")selected:String):Response<Gallery>
    /*suspend fun getCategories(@Query("key")key:String,@Query("category")category:String):Response<Gallery>*/
}