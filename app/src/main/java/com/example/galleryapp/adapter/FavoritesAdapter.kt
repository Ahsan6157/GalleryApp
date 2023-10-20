package com.example.galleryapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryapp.databinding.FavoriteItemsLayoutBinding
import com.example.galleryapp.listeners.FavoriteDetailListener
import com.example.galleryapp.model.Favorites
import com.squareup.picasso.Picasso

class FavoritesAdapter(private val favoritesList: List<Favorites?>,private val detailListener:FavoriteDetailListener,private val context:Context):RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    inner class FavoritesViewHolder(private val binding:FavoriteItemsLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Favorites){
            Glide.with(context).load(item.largeImageUrl).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressInd.visibility =
                        View.GONE // Hide ProgressBar on image load failure
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressInd.visibility = View.GONE // Hide ProgressBar on successful image load
                    binding.ivFavorites.visibility = View.VISIBLE
                    /*binding.iv1.visibility = View.VISIBLE
                    binding.iv2.visibility = View.VISIBLE*/
                    return false
                }
            }).into(binding.ivFavorites)
            binding.ivFavorites.setOnClickListener {
                detailListener.onFavoriteClick(item)
            }
//
            binding.ivDeleteFavorite.setOnClickListener {
            detailListener.delete(item.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemsLayoutBinding.inflate(inflater, parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return favoritesList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val image = favoritesList[position]
        val favoriteList =Favorites(image!!.id , image.id,image.largeImageUrl , image.likes , image.user , image.views , image.comments,image.downloads,image.tags )
        holder.bind(favoriteList)
    }
}