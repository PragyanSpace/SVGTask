package com.pragyan_space.simple_viral_games_assignment.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pragyan_space.simple_viral_games_assignment.databinding.DogImageRvItemBinding

class DogsRVAdapter(val context: Context,var data:List<Bitmap>):RecyclerView.Adapter<DogsRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:DogImageRvItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindView(image: Bitmap, context: Context, position: Int){
            Glide.with(context).load(image).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DogImageRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let {
            holder.bindView(it, context, position)
        }
    }

    fun clearData()
    {
        data= listOf()
        notifyDataSetChanged()
    }
}