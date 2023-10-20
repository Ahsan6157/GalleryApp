package com.example.galleryapp.listeners

import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit

interface Favorite {
 fun   isFavorite(hit: Hit,isFavorite:Boolean,id:Int)
 fun detials(hit: Hit,favorites: Favorites)

}