package com.saj.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.R
import com.saj.marvel.databinding.CharacterItemBinding
import com.saj.marvel.models.Character
import com.saj.marvel.ui.imageManager.ImageManager

class CharactersPagingAdapter(private val imageManager: ImageManager,
                              private val onClick: (Character) -> Unit,
                              diffCallback: DiffUtil.ItemCallback<Character>)
    : PagingDataAdapter<Character, CharactersPagingAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.character_item, parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)!!
        holder.binding.characterName.text = character.name
        holder.binding.characterDescription.text = character.description
        imageManager.loadImage(holder.itemView.context, holder.binding.characterThumbnail,
            character.thumbnail)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CharacterItemBinding.bind(itemView)
        init {
            binding.root.setOnClickListener {
                onClick.invoke(getItem(bindingAdapterPosition)!!)
            }
        }
    }
}