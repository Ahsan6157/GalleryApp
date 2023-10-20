package com.example.galleryapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.adapter.FavoritesAdapter
import com.example.galleryapp.GalleryApplication
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentFavoritesBinding
import com.example.galleryapp.listeners.FavoriteDetailListener
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Hit
import com.example.galleryapp.viewModel.FavoriteViewModel
import com.example.galleryapp.viewModel.FavoriteViewModelFactory

class FavoritesFragment : Fragment(), FavoriteDetailListener {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        val repo = (activity?.application as GalleryApplication).repository
        favoriteViewModel =
            ViewModelProvider(this, FavoriteViewModelFactory(repo))[FavoriteViewModel::class.java]
        favoriteViewModel.getFavorites().observe(viewLifecycleOwner) {
            adapter = FavoritesAdapter(it, this,requireContext())
            GridLayoutManager(requireContext(), 2)
            val recyclerViewFav: RecyclerView =
                binding.recyclerViewFavorites.findViewById(R.id.recyclerView)
            recyclerViewFav.adapter = adapter
        }
        return binding.root
    }

    override fun onFavoriteClick(favoritesDetail: Favorites) {
        val hit = Hit(
            favoritesDetail.id,
            0,
            favoritesDetail.largeImageUrl,
            favoritesDetail.likes,
            favoritesDetail.user,
            favoritesDetail.views,
            favoritesDetail.comments,
            favoritesDetail.downloads,
            favoritesDetail.tags,
            false
        )
        findNavController().navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                favoritesDetail, hit
            )
        )
    }

    override fun delete(favoritesId: Int) {
        favoriteViewModel.deleteFavorite(favoritesId)
    }
}