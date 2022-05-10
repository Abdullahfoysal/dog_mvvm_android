package com.example.dogmvvm.ui.RecycleView

import android.app.Application
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.dogmvvm.databinding.DogItemViewBinding
import com.example.dogmvvm.ui.dogs.DogActivity
import com.squareup.picasso.Picasso

import java.net.URL


class DogAdapter :RecyclerView.Adapter<DogAdapter.DogViewHolder>(){
    inner  class DogViewHolder(var binding: DogItemViewBinding): RecyclerView.ViewHolder(binding.root)

    private  val diffCallback= object: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var images: List<String>
    get() =differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
       return DogViewHolder(
           DogItemViewBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false,
           )
       )
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
       holder.binding.apply {
           val imageUrl = images[position]
           //retrofitTitle.text=imageUrl
           Picasso.get().load(imageUrl).into(imageView)
       }
    }

    override fun getItemCount(): Int {
      return images.size
    }
}