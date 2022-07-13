package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.viewstate.BookItemViewState
import com.example.bookapp.presentation.viewstate.BookItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val viewStateMapper: BookItemViewStateMapper
) : ViewModel() {

    private val _booksLiveData = MutableLiveData<List<BookItemViewState>>()
    val booksLiveData: LiveData<List<BookItemViewState>> = _booksLiveData

    init {
        refreshBooks()
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _booksLiveData.postValue(viewStateMapper(booksRepository.getBooksList()))
        }
    }

}