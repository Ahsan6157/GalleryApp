package com.example.galleryapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.model.Gallery
import com.example.galleryapp.model.Hit
import com.example.galleryapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: Repository):ViewModel() {
    /*private var default=seletectedCat
    private var category=categoriesList*/
 init {
fetchData("")
 }
//
    /*fun insertIsFavorite(isFavorite: Boolean,hit: Hit){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertIsFavorite(isFavorite,hit.id)
        }
    }*/
    /*fun checkFavorited(hit: Hit):Boolean{
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkIsFavoriteOrNot(hit.isFavorite)
        }
        return hit.isFavorite
    }*/
    fun fetchData(category:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getImagesData("39789799-b927a1b8b1ae7e115a87df54d",category)
        }
    }

    fun onSelectedCategoryClicked(i:Int){
    }

    val galleryData : LiveData<Gallery?>
        get() = repository.gallery
//
}