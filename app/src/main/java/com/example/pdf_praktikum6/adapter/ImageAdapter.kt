package com.example.pdf_praktikum6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pdf_praktikum6.databinding.ListSlideBinding
import com.example.pdf_praktikum6.model.ImageDataModel

class ImageAdapter(private val items: List<ImageDataModel>): RecyclerView.Adapter<ImageAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(item: ListSlideBinding): RecyclerView.ViewHolder(item.root)
    {
        private val binding = item

        fun bind(data: ImageDataModel)  {
            with(binding)  {
                Glide.with(itemView).load(data.imgUrl).into(imgGambar)
            }
        }

    }
}