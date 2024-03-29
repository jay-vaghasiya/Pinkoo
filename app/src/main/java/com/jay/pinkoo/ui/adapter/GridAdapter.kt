package com.jay.pinkoo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.pinkoo.databinding.ItemlistCategoryBinding
import com.jay.pinkoo.databinding.ItemlistGridListBinding
import com.jay.pinkoo.model.response.Category
import com.jay.pinkoo.model.response.userlist.Data

class GridAdapter(private val context: Context, private var imageList: List<Data>) :
    RecyclerView.Adapter<GridAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(binding: ItemlistGridListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.image
        val text=binding.tvProductName
        val price = binding.tvPrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemlistGridListBinding.inflate(inflater, parent, false)
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val image = imageList[position]

        Glide.with(context)
            .load(image.avatar)
            .centerCrop()
            .into(holder.imageView)
        holder.text.text=image.first_name
        holder.price.text="$${image.id}"
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}