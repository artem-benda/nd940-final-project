package ru.abenda.marsexplorer.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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