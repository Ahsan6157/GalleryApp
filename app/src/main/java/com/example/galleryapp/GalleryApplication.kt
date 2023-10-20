package com.example.galleryapp

import android.app.Application
import com.example.galleryapp.database.ImagesDatabase
import com.example.galleryapp.network.ImageService
import com.example.galleryapp.network.RetfofitHelper
import com.example.galleryapp.repository.Repository

class GalleryApplication:Application() {
     lateinit var repository: Repository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val galleryService= RetfofitHelper.getInstance().create(ImageService::class.java)
        val database= ImagesDatabase.getInstance(applicationContext)
        repository =  Repository(galleryService,database,applicationContext)


    }


}