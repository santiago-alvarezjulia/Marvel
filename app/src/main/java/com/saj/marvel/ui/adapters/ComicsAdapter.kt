package com.saj.marvel.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.R
import com.saj.marvel.databinding.ComicItemBinding
import com.saj.marvel.models.Comic
import javax.inject.Inject

class ComicsAdapter @Inject constructor() : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    private val comics = mutableListOf<Comic>()

    fun setData(newComics: List<Comic>) {
        val diffCallback = ComicDiffCallback(comics, newComics)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        comics.clear()
        comics.addAll(newComics)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = comics.size

    override fun getItemId(position: Int) = comics[position].id.toLong()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.comic_item, parent,
            false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.comicName.text = comic.name
        //todo: implement year
        holder.binding.comicYear.text = "1998"
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ComicItemBinding.bind(itemView)
    }
}