package com.jay.pinkoo.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jay.pinkoo.databinding.ItemlistCategoryBinding
import com.jay.pinkoo.model.response.Data

class CategoryAdapter(private var imageList: List<Data>) :
    RecyclerView.Adapter<CategoryAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(binding: ItemlistCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.image
        val text = binding.text

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemlistCategoryBinding.inflate(inflater, parent, false)
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val category = imageList[position]

        holder.text.text = category.name
        val color = category.color
        val colorInt = Color.parseColor(color)
        holder.imageView.setBackgroundColor(colorInt)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}