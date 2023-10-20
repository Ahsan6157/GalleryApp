package com.example.galleryapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryapp.R
import com.example.galleryapp.databinding.ImagesLayoutBinding
import com.example.galleryapp.listeners.Favorite
import com.example.galleryapp.model.Favorites
import com.example.galleryapp.model.Gallery
import com.example.galleryapp.model.Hit
class ImageAdapter(
    private val images: Gallery?,
    val favorite: Favorite,
    private val context: Context
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ImagesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isFav = false
        fun bind(item: Hit, position: Int) {
            val favorites = Favorites(
                0,
                item.id,
                item.largeImageURL,
                item.likes,
                item.user,
                item.views,
                item.comments,
                item.downloads,
                item.tags
            )
            binding.iv2.visibility = GONE
            Glide.with(context).load(item.largeImageURL).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility =
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
                    binding.progressBar.visibility =
                        View.GONE // Hide ProgressBar on successful image load
                    binding.iv1.visibility = VISIBLE
                    binding.iv2.visibility = VISIBLE
                    return false
                }
            }).into(binding.iv1)
            /*Glide.with().get().load(item.largeImageURL).into(binding.iv1, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = GONE
                    binding.iv1.visibility = VISIBLE
                    binding.iv2.visibility = VISIBLE
                    if(item.isFavorite){
                        binding.iv1.setImageResource(R.drawable.favorite_filled)
                    }
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = VISIBLE
                    binding.iv1.visibility = GONE
                    binding.iv2.visibility = GONE

                }
            })*/
            /*LISTENER ON FAVORITE IMAGE*/
            binding.iv2.setOnClickListener {
                if (!item.isFavorite) {
                    isFav = true
                    favorite.isFavorite(item, isFav, item.id)
                    binding.iv2.setImageResource(R.drawable.favorite_filled)
                    notifyItemChanged(position)
                } else {
                    favorite.isFavorite(item, isFav, item.id)
                    notifyItemChanged(position)
                    binding.iv2.setImageResource(R.drawable.favorite)


                }
            }

            /*listener of actual image*/
            binding.iv1.setOnClickListener {
                favorite.detials(item, favorites)
            }
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImagesLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images!!.hits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images!!.hits[position]
        holder.bind(item, position)
    }
}