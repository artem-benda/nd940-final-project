package ru.abenda.marsexplorer.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.abenda.marsexplorer.data.api.NetworkCallState
import ru.abenda.marsexplorer.data.api.trackCallState
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.repository.RoverManifestRepository
import javax.inject.Inject

private const val ROVER_TYPE = "rover_type"

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val roverManifestRepository: RoverManifestRepository
) : ViewModel() {

    fun setRoverType(roverType: RoverType) {
        savedStateHandle.set(ROVER_TYPE, roverType)
        launchManifestRefresh(roverType)
    }

    val photosStatsBySol = savedStateHandle.getLiveData<RoverType>(ROVER_TYPE)
        .switchMap { roverType ->
            roverManifestRepository.getPhotosStatsBySol(roverType)
                .asLiveData()
        }

    private val _refreshState = MutableLiveData<NetworkCallState>()
    val refreshState: LiveData<NetworkCallState> = _refreshState

    fun launchManifestRefresh(roverType: RoverType) {
        viewModelScope.launch {
            _refreshState.trackCallState {
                roverManifestRepository.refreshManifest(roverType)
            }
        }
    }
}
