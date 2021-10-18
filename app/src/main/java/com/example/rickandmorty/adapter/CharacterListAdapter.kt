package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.GetCharactersQuery
import com.example.rickandmorty.MyApplication
import com.example.rickandmorty.databinding.CharacterListItemBinding

class CharacterListAdapter(
    private val dataset: ArrayList<GetCharactersQuery.Result?>
    ): RecyclerView.Adapter<CharacterListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: CharacterListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetCharactersQuery.Result?) {
            binding.data = data
            // make sure to include this so your view will be updated
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterListItemBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.bind(item)
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindImage(imageView: ImageView, imageUrl: String) {
            Glide.with(MyApplication.applicationContext())
                .load(imageUrl)
                .into(imageView)
        }
    }

    override fun getItemCount() = dataset.size
}