package ru.abenda.marsexplorer.ui.photos

import androidx.lifecycle.ViewModel
import ru.abenda.marsexplorer.data.repository.NasaMarsRoverRepository
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val photosRepository: NasaMarsRoverRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}
