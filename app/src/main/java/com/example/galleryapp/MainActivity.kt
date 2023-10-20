package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.galleryapp.databinding.ActivityMainBinding
import com.example.galleryapp.viewModel.GalleryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryViewModel: GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        /*val repo = ( this.application as com.example.galleryapp.GalleryApplication).repository
        galleryViewModel = ViewModelProvider(this, GalleryViewModelFactory(repo))[GalleryViewModel::class.java]*/
    }


}