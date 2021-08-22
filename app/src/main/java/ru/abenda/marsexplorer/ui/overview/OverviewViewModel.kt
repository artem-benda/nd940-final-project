package ru.abenda.marsexplorer.ui.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.abenda.marsexplorer.data.repository.RoverPhotosRepository

@HiltViewModel
class OverviewViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val roverPhotosRepository: RoverPhotosRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}