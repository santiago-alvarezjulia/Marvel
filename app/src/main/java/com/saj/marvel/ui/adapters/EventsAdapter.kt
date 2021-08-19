package com.saj.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saj.marvel.R
import com.saj.marvel.databinding.EventItemBinding
import com.saj.marvel.models.Event
import com.saj.marvel.ui.imageManager.ImageManager
import javax.inject.Inject

class EventsAdapter @Inject constructor(
    private val imageManager: ImageManager
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private val events = mutableListOf<Event>()

    fun setData(newEvents: List<Event>) {
        val diffCallback = EventDiffCallback(events, newEvents)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        events.clear()
        events.addAll(newEvents)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = events.size

    override fun getItemId(position: Int) = events[position].id.toLong()

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
        val event = events[position]
        holder.binding.eventTitle.text = event.title
        holder.binding.eventDate.text = event.startDate
        imageManager.loadImage(holder.itemView.context, holder.binding.eventThumbnail,
            event.thumbnail)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = EventItemBinding.bind(itemView)
    }
}