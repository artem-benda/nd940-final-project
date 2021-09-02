package ru.abenda.marsexplorer.ui

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail
import ru.abenda.marsexplorer.data.db.model.RoverPhoto

@BindingAdapter("loadImage")
fun ImageView.loadImage(roverPhoto: RoverPhoto?) {
    roverPhoto ?: return

    Glide
        .with(this)
        .load(roverPhoto.imageSrc)
        .centerCrop()
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        //.error(R.drawable.ic_pic_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

@BindingAdapter("loadThumbnail")
fun ImageView.loadThumbnail(thumbnail: PhotosStatsBySolThumbnail?) {
    thumbnail ?: return

    Glide
        .with(this)
        .load(thumbnail.imageSrc)
        .centerCrop()
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        //.error(R.drawable.ic_pic_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

@BindingAdapter("showErrorText")
fun TextView.showErrorText(loadState: LoadState?) {
    visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
    text = (loadState as? LoadState.Error)?.error?.localizedMessage ?: ""
}

@BindingAdapter("setProgressVisibility")
fun ProgressBar.setProgressVisibility(loadState: LoadState?) {
    visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("setRetryVisibility")
fun Button.setRetryVisibility(loadState: LoadState?) {
    visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
}
