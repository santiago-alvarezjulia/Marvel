package com.saj.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.R
import com.saj.marvel.databinding.CharacterItemBinding
import com.saj.marvel.models.Character
import javax.inject.Inject

class CharactersAdapter @Inject constructor()
    : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val characters = mutableListOf<Character>()

    fun setData(newCharacters: List<Character>) {
        val diffCallback = CharacterDiffCallback(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters.clear()
        characters.addAll(newCharacters)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.characterName.text = character.name
        holder.binding.characterDescription.text = character.description
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CharacterItemBinding.bind(itemView)
    }
}