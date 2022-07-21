package com.example.bookapp.presentation.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.BookPreviewViewState
import com.example.bookapp.presentation.viewstate.BookPreviewViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookPreviewViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val viewStateMapper: BookPreviewViewStateMapper
) : ViewModel() {

    private val _bookLiveData = MutableLiveData<LoadingResult<BookPreviewViewState>>()
    val bookLiveData: LiveData<LoadingResult<BookPreviewViewState>> = _bookLiveData

    fun loadBookById(bookId: Int) {
        viewModelScope.launch {
            try {
                _bookLiveData.postValue(LoadingResult.Loading)
                _bookLiveData.postValue(
                    LoadingResult.Success(
                        viewStateMapper(booksRepository.getBookById(bookId))
                    )
                )
            } catch (e: Exception) {
                _bookLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }
}