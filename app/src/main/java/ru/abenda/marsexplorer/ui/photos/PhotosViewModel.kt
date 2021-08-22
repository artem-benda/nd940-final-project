package ru.abenda.marsexplorer.ui.photos

import androidx.lifecycle.ViewModel
import ru.abenda.marsexplorer.data.repository.RoverPhotosRepository
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val photosRepository: RoverPhotosRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}
