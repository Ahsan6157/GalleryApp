package com.example.galleryapp.model

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("images_tables")
data class Hit(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("imagesId")
    val imagesId: Int,
    @ColumnInfo("imageUrl")
    val largeImageURL:String,
    @ColumnInfo("likes")
    val likes: Int,
    @ColumnInfo("user")
    val user: String,
    @ColumnInfo("views")
    val views: Int,
    @ColumnInfo("comments")
    val comments: Int,
    @ColumnInfo("downloads")
    val downloads: Int,
    @ColumnInfo("tags")
    val tags: String,
    @ColumnInfo("isFavorite")
    var isFavorite:Boolean
):Serializable