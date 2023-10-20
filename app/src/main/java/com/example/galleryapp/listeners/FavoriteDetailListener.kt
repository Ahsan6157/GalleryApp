package com.example.galleryapp.listeners

import com.example.galleryapp.model.Favorites

interface FavoriteDetailListener {
  fun onFavoriteClick(favoritesDetail:Favorites)
  fun delete(favoritesId: Int)
}