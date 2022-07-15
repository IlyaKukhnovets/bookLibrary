package com.example.bookapp.presentation.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.BookItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookPreviewViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val viewStateMapper: BookItemViewStateMapper
) : ViewModel() {

    private val _bookLiveData = MutableLiveData<LoadingResult<Any>>()
    val bookLiveData: LiveData<LoadingResult<Any>> = _bookLiveData

    fun loadBookById(bookId: Int) {
        viewModelScope.launch {
            try {
                _bookLiveData.postValue(LoadingResult.Loading)
                _bookLiveData.postValue(LoadingResult.Success(
                        viewStateMapper(booksRepository.getBookById(bookId)))
                )
            } catch (e: Exception) {
                _bookLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }
}