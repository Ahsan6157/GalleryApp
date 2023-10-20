package com.example.galleryapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit
import com.example.galleryapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun insertFavorite(hit: Hit) {
        val favorite =
            Favorites(
                0,
                hit.id,
                hit.largeImageURL,
                hit.likes,
                hit.user,
                hit.views,
                hit.comments,
                hit.downloads,
                hit.tags
            )

        viewModelScope.launch(Dispatchers.IO) {
            val favId = repository.duplicateId(hit.id)
            if (favId == null) {
                repository.insertFavorite(favorite)
            }
        }
    }

    fun getFavorites(): LiveData<List<Favorites>> {
        return repository.getFavoriteImages()
    }


/*    fun isFavoriteImage(isFavorite: Boolean, id: Int){
        repository.insertIsFavorite(isFavorite,id)
    }*/


    fun deleteFavorite(favoritesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorites(favoritesId)
        }
    }
}