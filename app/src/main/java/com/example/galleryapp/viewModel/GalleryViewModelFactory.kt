package com.example.galleryapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryapp.repository.Repository

class GalleryViewModelFactory(
    private val repository: Repository,
   /*private val selected: String,
    private val categories: List<String>*/
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}