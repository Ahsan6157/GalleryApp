package com.example.galleryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.galleryapp.model.Gallery
import com.example.galleryapp.model.Hit

@Dao
interface HitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(hit: List<Hit>)

    @Query("select * from images_tables")
      fun getAllImages():List<Hit>
//      check whether the images id already exists coming from api's
      @Query("select * from images_tables where id=:id")
      fun checkId( id: Int):Hit

      /*@Update(Hit::class)
      fun update()*/
      @Query("update images_tables set isFavorite=:isFavorite where id =:id")
     fun updateFavorite(isFavorite:Boolean,id:Int)
     /*@Query("select * from images_tables where isFavorite=:value")
     suspend fun checkIsFavoriteOrNot(value: Boolean)*/
}