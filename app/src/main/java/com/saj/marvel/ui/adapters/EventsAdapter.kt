package com.saj.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.R
import com.saj.marvel.databinding.EventItemBinding
import com.saj.marvel.ui.imageManager.ImageManager
import com.saj.marvel.ui.models.ListedEvent
import javax.inject.Inject

class EventsAdapter @Inject constructor(
    private val imageManager: ImageManager
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private val listedEvents = mutableListOf<ListedEvent>()

    fun setData(newListedEvents: List<ListedEvent>) {
        val diffCallback = EventDiffCallback(listedEvents, newListedEvents)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listedEvents.clear()
        listedEvents.addAll(newListedEvents)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = listedEvents.size

    override fun getItemId(position: Int) = listedEvents[position].getId().toLong()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.event_item, parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = listedEvents[position]
        holder.binding.eventTitle.text = event.getTitle()
        holder.binding.eventDate.text = event.getStartDate()
        if (event.isExpanded) {
            holder.binding.expandItem.setImageResource(R.drawable.ic_outline_expand_less_24)
            holder.binding.expandGroup.visibility = View.VISIBLE
        } else {
            holder.binding.expandItem.setImageResource(R.drawable.ic_outline_expand_more_24)
            holder.binding.expandGroup.visibility = View.GONE
        }
        imageManager.loadImage(holder.itemView.context, holder.binding.eventThumbnail,
            event.getThumbnail())
        holder.comicsAdapter.setData(event.getComics())
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = EventItemBinding.bind(itemView)
        val comicsAdapter = ComicsAdapter()

        init {
            binding.expandItem.setOnClickListener {
                listedEvents[adapterPosition].isExpanded = !listedEvents[adapterPosition].isExpanded
                notifyItemChanged(adapterPosition)
            }
            setUpComicsAdapter()
        }

        private fun setUpComicsAdapter() {
            val layoutManager = LinearLayoutManager(itemView.context)
            binding.comicsToDiscussList.layoutManager = layoutManager
            comicsAdapter.setHasStableIds(true)
            binding.comicsToDiscussList.adapter = comicsAdapter
        }
    }
}