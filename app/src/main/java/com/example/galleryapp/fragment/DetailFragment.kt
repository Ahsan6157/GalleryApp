package com.example.galleryapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        Picasso.get().load(args.detailsData.largeImageUrl).into(binding.ivImage)
        binding.tvLikes.text = args.detailsData.likes.toString()
        binding.tvViews.text = args.detailsData.views.toString()
        binding.tvComments.text = args.detailsData.comments.toString()
        binding.tvDownloads.text = args.detailsData.comments.toString()
        binding.tvTags.text = args.detailsData.tags

        return binding.root
    }
}