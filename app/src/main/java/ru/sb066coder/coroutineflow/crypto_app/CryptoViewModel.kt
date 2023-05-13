package ru.sb066coder.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

//    val state: LiveData<State> = repository.getCurrencyList()
//        .filter { it.isNotEmpty() }
//        .map { State.Content(currencyList = it) as State }
//        .onStart { emit(State.Loading) }
//        .asLiveData()

    val state: Flow<State> =  repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart {
            Log.d("CryptoViewModel", "Started")
            emit(State.Loading)
        }
        .onEach {
            Log.d("CryptoViewModel", "onEach")
        }
        .onCompletion {
            Log.d("CryptoViewModel", "Complete")
        }
}
