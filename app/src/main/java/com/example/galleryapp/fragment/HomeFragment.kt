package com.example.galleryapp.fragment

import com.example.galleryapp.GalleryApplication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.adapter.CategoryAdapter
import com.example.galleryapp.adapter.ImageAdapter
import com.example.galleryapp.R
import com.example.galleryapp.R.id.action_homeFragment2_to_favoritesFragment
import com.example.galleryapp.databinding.FragmentHomeBinding
import com.example.galleryapp.listeners.CategorySelectedListener
import com.example.galleryapp.listeners.Favorite
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit
import com.example.galleryapp.viewModel.FavoriteViewModel
import com.example.galleryapp.viewModel.FavoriteViewModelFactory
import com.example.galleryapp.viewModel.GalleryViewModel
import com.example.galleryapp.viewModel.GalleryViewModelFactory

class HomeFragment : Fragment(), Favorite, CategorySelectedListener {
    private lateinit var adapter: ImageAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private val categories = listOf(
        "backgrounds",
        "nature",
        "science",
        "education",
        "feelings",
        "health",
        "people",
        "religion",
        "places",
        "animals",
        "industry",
        "computer"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val repo = (activity?.application as GalleryApplication).repository
        galleryViewModel =
            ViewModelProvider(this, GalleryViewModelFactory(repo))[GalleryViewModel::class.java]
//      favorite viewmodel
        favoriteViewModel =
            ViewModelProvider(this, FavoriteViewModelFactory(repo))[FavoriteViewModel::class.java]
// observer for all images
        /*GridLayoutManager(requireContext(), 2)*/
        galleryViewModel.galleryData.observe(viewLifecycleOwner) {
            adapter = ImageAdapter(it, this,requireContext())
            val recyclerView: RecyclerView =
                binding.homeRecyclerView.findViewById(R.id.recyclerView)
            recyclerView.adapter = adapter
        }
//        only favorite images observer
        categoryAdapter = CategoryAdapter(categories, this)
        LinearLayoutManager.HORIZONTAL
        val categoriesRv: RecyclerView = binding.categoriesRv
        categoriesRv.adapter = categoryAdapter

        binding.toolBar.inflateMenu(R.menu.menu)
        binding.toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.iconFav -> {
                    findNavController().navigate(action_homeFragment2_to_favoritesFragment)
                    true
                }

                else -> false
            }
        }

        return binding.root
    }

    override fun isFavorite(hit: Hit, isFavorite: Boolean, id: Int) {
        favoriteViewModel.insertFavorite(hit)
        /*favoriteViewModel.isFavoriteImage(isFavorite,hit.id)*/
    }

    override fun detials(hit: Hit, favorites: Favorites) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragment2ToDetailFragment(
                favorites,
                hit
            )
        )
    }


    override fun selectCategory(categ: String) {
        galleryViewModel.fetchData(categ)
    }
}
