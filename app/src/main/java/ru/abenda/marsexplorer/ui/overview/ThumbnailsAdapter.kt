package ru.abenda.marsexplorer.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.databinding.ListItemThumbnailBinding

class ThumbnailsAdapter :
    ListAdapter<RoverPhoto, ThumbnailsAdapter.ThumbnailListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RoverPhoto>() {
        override fun areItemsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
            return oldItem == newItem
        }
    }

    class ThumbnailListViewHolder(private var binding: ListItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roverPhoto: RoverPhoto) {
            binding.roverPhoto = roverPhoto
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ThumbnailListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemThumbnailBinding.inflate(layoutInflater, parent, false)
                return ThumbnailListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailListViewHolder {
        return ThumbnailListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ThumbnailListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
