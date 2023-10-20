package com.example.galleryapp.adapter

import android.content.Context
import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.CategoriesItemsBinding
import com.example.galleryapp.listeners.CategorySelectedListener

class CategoryAdapter(  val catagoriesList:List<String>,val onSelectedCategory:CategorySelectedListener):RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
   inner class ViewHolder(val binding:CategoriesItemsBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(item:String){
            binding.tvCategories.text=item
           binding.tvCategories.setOnClickListener {
               onSelectedCategory.selectCategory(item)
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =CategoriesItemsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return catagoriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val item = catagoriesList[position]
        holder.bind(item)
    }


}