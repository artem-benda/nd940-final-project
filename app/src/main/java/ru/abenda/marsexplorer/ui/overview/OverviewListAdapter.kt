package ru.abenda.marsexplorer.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.data.db.model.composite.PhotosStatsBySolWithThumbnails
import ru.abenda.marsexplorer.databinding.ListItemOverviewBinding
import timber.log.Timber

class OverviewListAdapter(private val itemListener: OverviewListItemListener) :
    ListAdapter<PhotosStatsBySolWithThumbnails, OverviewListAdapter.OverviewListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PhotosStatsBySolWithThumbnails>() {
        override fun areItemsTheSame(oldItem: PhotosStatsBySolWithThumbnails, newItem: PhotosStatsBySolWithThumbnails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotosStatsBySolWithThumbnails, newItem: PhotosStatsBySolWithThumbnails): Boolean {
            return oldItem.photosStatsBySol == newItem.photosStatsBySol && oldItem.thumbnails.size == newItem.thumbnails.size
        }
    }

    class OverviewListViewHolder(private var binding: ListItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OverviewListItemListener, statsBySol: PhotosStatsBySolWithThumbnails) {
            binding.statsBySol = statsBySol
            binding.clickListener = listener

            Timber.d("Setting %d thumbnails", statsBySol.thumbnails.size)
            (binding.thumbnails.adapter as ThumbnailsAdapter).submitList(statsBySol.thumbnails)
            binding.executePendingBindings()

            listener.onBind(statsBySol)
        }

        companion object {
            fun from(parent: ViewGroup): OverviewListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOverviewBinding.inflate(layoutInflater, parent, false)

                binding.thumbnails.adapter = ThumbnailsAdapter()

                return OverviewListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewListViewHolder {
        return OverviewListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OverviewListViewHolder, position: Int) {
        holder.bind(itemListener, getItem(position))
    }
}

class OverviewListItemListener(
    val clickListener: (statsBySol: PhotosStatsBySolWithThumbnails) -> Unit,
    val bindCallback: (statsBySol: PhotosStatsBySolWithThumbnails) -> Unit
) {
    fun onClick(statsBySol: PhotosStatsBySolWithThumbnails) = clickListener(statsBySol)
    fun onBind(statsBySol: PhotosStatsBySolWithThumbnails) = bindCallback(statsBySol)
}
