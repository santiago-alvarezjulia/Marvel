package com.saj.marvel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.saj.marvel.models.Character

class CharacterDiffCallback(private val oldList: List<Character>, private val newList: List<Character>)
    : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isTheSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isContentTheSame(newList[newItemPosition])
    }
}