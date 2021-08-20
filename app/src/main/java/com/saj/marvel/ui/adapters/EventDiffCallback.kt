package com.saj.marvel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.saj.marvel.ui.models.ListedEvent

class EventDiffCallback(private val oldList: List<ListedEvent>, private val newList: List<ListedEvent>)
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