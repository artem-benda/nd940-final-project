package ru.abenda.marsexplorer.data.api

sealed class NetworkCallState {
    data class Success<T> (val result: T) : NetworkCallState()
    data class Error<E : Throwable> (val error: E) : NetworkCallState()
    object Pending : NetworkCallState()
}
