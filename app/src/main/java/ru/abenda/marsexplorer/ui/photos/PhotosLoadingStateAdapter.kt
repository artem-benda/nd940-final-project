package ru.abenda.marsexplorer.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.databinding.PhotosLoadStateFooterViewItemBinding

class PhotosLoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotosLoadingStateAdapter.PhotosLoadingStateViewHolder>() {
    override fun onBindViewHolder(holder: PhotosLoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PhotosLoadingStateViewHolder {
        return PhotosLoadingStateViewHolder.from(parent, retry)
    }

    class PhotosLoadingStateViewHolder(
        private val binding: PhotosLoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.loadState = loadState
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, retry: () -> Unit): PhotosLoadingStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.photos_load_state_footer_view_item, parent, false)
                val binding = PhotosLoadStateFooterViewItemBinding.bind(view)
                return PhotosLoadingStateViewHolder(binding, retry)
            }
        }
    }
}
