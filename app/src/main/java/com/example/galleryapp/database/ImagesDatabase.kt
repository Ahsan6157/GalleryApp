package com.example.galleryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit

@Database([Hit::class,Favorites::class], version = 1, exportSchema = false)
abstract class ImagesDatabase:RoomDatabase() {
//
    abstract fun getImages(): HitDao
    abstract fun getFavorites(): FavoriteDao
    companion object{
        @Volatile
        private var INSTANCE: ImagesDatabase?=null
        fun getInstance(context: Context): ImagesDatabase {

            if (INSTANCE ==null){
            synchronized(this){
                INSTANCE =Room.databaseBuilder(context.applicationContext,
                    ImagesDatabase::class.java,"ImagesDatabase").build()
            }
            }
            return INSTANCE!!
        }
    }
}