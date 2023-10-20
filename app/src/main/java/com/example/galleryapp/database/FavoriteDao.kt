package com.example.galleryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit

@Dao
interface FavoriteDao {
    @Insert()
    suspend fun insertFavorite(favorites: Favorites)
//
    @Query("select * from favorites")
    fun getAllFavorites():LiveData<List<Favorites>>
//
    @Query("select * from favorites where id=:id")
    suspend fun checkIdFromFavorites( id: Int):Favorites?
//
    @Query("delete from favorites where id=:id")
     fun delete(id:Int)
}