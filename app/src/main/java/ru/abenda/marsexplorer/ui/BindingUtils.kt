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
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.data.api.NetworkCallState
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.data.enums.CameraType
import timber.log.Timber

@BindingAdapter("loadImage")
fun ImageView.loadImage(roverPhoto: RoverPhoto?) {
    roverPhoto ?: return

    Glide
        .with(this)
        .load(roverPhoto.imageSrc)
        .centerCrop()
        .placeholder(R.drawable.ic_loading)
        .error(R.drawable.ic_error)
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
        .placeholder(R.drawable.ic_loading)
        .error(R.drawable.ic_error)
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

@BindingAdapter("setProgressVisibility")
fun ProgressBar.setProgressVisibility(networkCallState: NetworkCallState?) {
    Timber.d("setProgressVisibility, networkCallState = %s", networkCallState)
    val isPending = networkCallState is NetworkCallState.Pending
    Timber.d("setProgressVisibility, isPending = %b", isPending)
    visibility = if (isPending) View.VISIBLE else View.GONE
}

@BindingAdapter("formatCameraType")
fun TextView.formatCameraType(cameraType: CameraType?) {
    cameraType ?: return

    val cameraName = context.getString(cameraType.cameraNameResId)
    text = context.getString(R.string.camera_type_template, cameraName)
}