package com.example.galleryapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.database.ImagesDatabase
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Gallery
import com.example.galleryapp.model.Hit
import com.example.galleryapp.network.ImageService
import com.example.galleryapp.utils.NetworkUtils

class Repository(
    private val imageService: ImageService,
    private val database: ImagesDatabase,
    private val applicationContext: Context
) {
    private val galleryData = MutableLiveData<Gallery?>()
    private var favoriteData = MutableLiveData<List<Favorites?>>()

    //    for images
    val gallery: LiveData<Gallery?>
        get() = galleryData

    // for favorite images
    val favorite: LiveData<List<Favorites?>>
        get() = favoriteData

    suspend fun getImagesData(key: String, selected: String) {
        if (NetworkUtils.isOnline(applicationContext)) {
            val result = imageService.getImages(key, selected)
            if (result.isSuccessful) {
                for (resp in result.body()!!.hits) {
                    if (!resp.id.equals(database.getImages().checkId(resp.id))) {
                        database.getImages().insertImages(result.body()!!.hits)
                    }
                }
                /*database.getImages().insertImages(result.body()!!.hits)*/
                galleryData.postValue(result.body())
            }
        } else {
            val dbData: List<Hit> = database.getImages().getAllImages()
            val galleryList = Gallery(dbData, 0, 0)
            galleryData.postValue(galleryList)
        }
    }

    suspend fun insertFavorite(favorite: Favorites) {
        database.getFavorites().insertFavorite(favorite)
    }

    /*fun insertIsFavorite(value: Boolean, id: Int) {
        database.getImages().updateFavorite(value, id)
    }*/

    fun getFavoriteImages(): LiveData<List<Favorites>> {
        return database.getFavorites().getAllFavorites()
    }

    //check duplicate id from database
    suspend fun duplicateId(id: Int): Favorites? {
        return database.getFavorites().checkIdFromFavorites(id)
    }

    fun deleteFavorites(id: Int) {
        return database.getFavorites().delete(id)
    }

}