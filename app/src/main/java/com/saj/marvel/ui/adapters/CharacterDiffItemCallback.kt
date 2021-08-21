package com.saj.marvel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.saj.marvel.models.Character

class CharacterDiffItemCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.isTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.isContentTheSame(newItem)
    }
}