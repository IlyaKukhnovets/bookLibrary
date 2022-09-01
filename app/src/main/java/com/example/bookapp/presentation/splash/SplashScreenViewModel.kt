package com.example.bookapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.state.LoadingResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _delayLiveData = MutableLiveData<LoadingResult<Boolean>>()
    val delayLiveData: LiveData<LoadingResult<Boolean>> = _delayLiveData

    init {
        viewModelScope.launch {
            _delayLiveData.postValue(LoadingResult.Loading)
            try {
                delay(4000)
                _delayLiveData.postValue(LoadingResult.Success(true))
            } catch (e: Exception) {
                _delayLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }
}