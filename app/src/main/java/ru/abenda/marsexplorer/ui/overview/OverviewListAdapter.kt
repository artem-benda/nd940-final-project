package ru.abenda.marsexplorer.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.databinding.ListItemOverviewBinding

class OverviewListAdapter(private val clickListener: OverviewClickListener) :
    ListAdapter<PhotosStatsBySol, OverviewListAdapter.OverviewListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PhotosStatsBySol>() {
        override fun areItemsTheSame(oldItem: PhotosStatsBySol, newItem: PhotosStatsBySol): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotosStatsBySol, newItem: PhotosStatsBySol): Boolean {
            return oldItem == newItem
        }
    }

    class OverviewListViewHolder(private var binding: ListItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OverviewClickListener, statsBySol: PhotosStatsBySol) {
            binding.statsBySol = statsBySol
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): OverviewListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOverviewBinding.inflate(layoutInflater, parent, false)
                return OverviewListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewListViewHolder {
        return OverviewListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OverviewListViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }
}

class OverviewClickListener(val clickListener: (statsBySol: PhotosStatsBySol) -> Unit) {
    fun onClick(statsBySol: PhotosStatsBySol) = clickListener(statsBySol)
}
