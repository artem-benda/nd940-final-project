package ru.abenda.marsexplorer.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.databinding.ListItemPhotoBinding

class PagingPhotosAdapter(private val itemListener: PhotoListItemListener) :
    PagingDataAdapter<RoverPhoto, PagingPhotosAdapter.RoverPhotoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RoverPhoto>() {
        override fun areItemsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
            return oldItem.imageSrc == newItem.imageSrc
        }

        override fun areContentsTheSame(oldItem: RoverPhoto, newItem: RoverPhoto): Boolean {
            return oldItem.imageSrc == newItem.imageSrc
        }
    }

    class RoverPhotoViewHolder(private var binding: ListItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: PhotoListItemListener, photo: RoverPhoto) {
            binding.photo = photo
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RoverPhotoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPhotoBinding.inflate(layoutInflater, parent, false)
                return RoverPhotoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverPhotoViewHolder {
        return RoverPhotoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoverPhotoViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(itemListener, it)
        }
    }
}

class PhotoListItemListener(
    val clickListener: (photo: RoverPhoto) -> Unit
) {
    fun onClick(photo: RoverPhoto) = clickListener(photo)
}
