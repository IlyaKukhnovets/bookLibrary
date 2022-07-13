package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.BookItemViewState
import com.example.bookapp.presentation.viewstate.BookItemViewStateMapper
import com.example.bookapp.presentation.viewstate.BookStatus
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyFavouriteBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val mapper: BookItemViewStateMapper
) : ViewModel() {

    private val _favouriteBooksLiveData = MutableLiveData<LoadingResult<List<BookItemViewState>>>()
    val favouriteBooksLiveData: LiveData<LoadingResult<List<BookItemViewState>>> = _favouriteBooksLiveData

    init {
        refreshBooks()
    }

    fun refreshBooks() {
        viewModelScope.launch {
            try {
                _favouriteBooksLiveData.postValue(LoadingResult.Success(
                    mapper(booksRepository.getBooksByStatus(BookStatus.FAVOURITE.status)))
                )
            } catch (e: Exception) {
                _favouriteBooksLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }

}