package ru.abenda.marsexplorer.data.api

import androidx.lifecycle.MutableLiveData

suspend fun <T> MutableLiveData<NetworkCallState>.trackCallState(call: suspend () -> Result<T>) {
    value = NetworkCallState.Pending
    call()
        .onSuccess {
            value = NetworkCallState.Success(it)
        }
        .onFailure {
            value = NetworkCallState.Error(it)
        }
}
