package com.example.galleryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("favorites")
data class Favorites(
    @ColumnInfo("autoId")
    @PrimaryKey(autoGenerate = true)
    val autoId: Int,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("largeImageUrl")
    val largeImageUrl: String,
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

    ):Serializable