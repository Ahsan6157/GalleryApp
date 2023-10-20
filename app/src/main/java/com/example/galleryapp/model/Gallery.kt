package com.example.galleryapp.model

import androidx.lifecycle.LiveData

data class Gallery(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)