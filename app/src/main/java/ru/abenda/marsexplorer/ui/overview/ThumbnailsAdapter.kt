package ru.abenda.marsexplorer.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail
import ru.abenda.marsexplorer.databinding.ListItemThumbnailBinding

class ThumbnailsAdapter :
    ListAdapter<PhotosStatsBySolThumbnail, ThumbnailsAdapter.ThumbnailListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PhotosStatsBySolThumbnail>() {
        override fun areItemsTheSame(oldItem: PhotosStatsBySolThumbnail, newItem: PhotosStatsBySolThumbnail): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotosStatsBySolThumbnail, newItem: PhotosStatsBySolThumbnail): Boolean {
            return oldItem == newItem
        }
    }

    class ThumbnailListViewHolder(private var binding: ListItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(thumbnail: PhotosStatsBySolThumbnail) {
            binding.thumbnail = thumbnail
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
