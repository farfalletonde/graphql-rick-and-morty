package com.example.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.GetCharactersQuery
import com.example.rickandmorty.R

class CharacterListAdapter(
    private val dataset: List<GetCharactersQuery.Result?>,
    private val context: Context
    ): RecyclerView.Adapter<CharacterListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val characterImage: ImageView = view.findViewById(R.id.characterImage)
        val characterId: TextView = view.findViewById(R.id.idOfCharacter)
        val characterName: TextView = view.findViewById(R.id.nameOfCharacter)
        val characterLocation: TextView = view.findViewById(R.id.locationOfCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.apply {
            Glide.with(context).load(item!!.image).into(characterImage)
            characterId.text = item.id
            characterName.text = item.name
            characterLocation.text = item.location!!.name
        }
    }

    override fun getItemCount() = dataset.size
}