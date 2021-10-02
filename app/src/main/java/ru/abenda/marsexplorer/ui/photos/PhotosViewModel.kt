package ru.abenda.marsexplorer.ui.photos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.repository.RoverPhotosRepository
import javax.inject.Inject

const val PARAMS_KEY = "params"

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosRepository: RoverPhotosRepository
) : ViewModel() {
    fun fetchPhotos(roverType: RoverType, sol: Int): Flow<PagingData<RoverPhoto>> {
        return photosRepository.getSearchResultStream(
            roverType,
            null,
            sol
        ).cachedIn(viewModelScope)
    }

    data class Params(
        val roverType: RoverType,
        val sol: Int
    )
}
