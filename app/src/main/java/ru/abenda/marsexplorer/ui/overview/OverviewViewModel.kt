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
import timber.log.Timber
import javax.inject.Inject

private const val ROVER_TYPE = "rover_type"

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val roverManifestRepository: RoverManifestRepository
) : ViewModel() {

    fun setRoverType(roverType: RoverType) {
        Timber.i("setRoverType, roverType = %s", roverType)
        savedStateHandle.set(ROVER_TYPE, roverType)
        launchManifestRefresh(roverType)
    }

    val manifest = savedStateHandle.getLiveData<RoverType>(ROVER_TYPE)
        .switchMap { roverType ->
            Timber.i("manifest reload, roverType = %s", roverType)
            roverManifestRepository.getManifestFlow(roverType)
                .asLiveData()
        }

    private val _refreshState = MutableLiveData<NetworkCallState>()
    val refreshState: LiveData<NetworkCallState> = _refreshState

    fun launchManifestRefresh(roverType: RoverType) {
        viewModelScope.launch {
            _refreshState.trackCallState {
                Timber.i("refreshManifest... roverType = %s", roverType)
                roverManifestRepository.refreshManifest(roverType)
            }
        }
    }

    fun launchLoadThumbnails(roverType: RoverType, sol: Int) {
        viewModelScope.launch {
            Timber.i("refreshThumbnailsIfAbsent... roverType = %s, sol = %d", roverType, sol)
            roverManifestRepository.refreshThumbnailsIfAbsent(roverType, sol)
        }
    }
}
