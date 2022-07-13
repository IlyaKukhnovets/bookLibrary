package com.example.bookapp.data.state

sealed class LoadingResult<out R> {

    companion object {
        fun success(): LoadingResult<Unit> = Success(Unit)
    }

    data class Success<out T>(val data: T) : LoadingResult<T>()
    data class Error(val exception: Exception) : LoadingResult<Nothing>()
    object Loading : LoadingResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}