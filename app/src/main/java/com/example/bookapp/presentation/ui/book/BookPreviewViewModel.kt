package com.example.bookapp.presentation.ui.book

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.book.BookPreviewViewState
import com.example.bookapp.presentation.viewstate.book.BookPreviewViewStateMapper
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewStateMapper
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class BookPreviewViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookViewStateMapper: BookPreviewViewStateMapper,
    private val bookSeriesViewStateMapper: BooksSeriesViewStateMapper
) : ViewModel() {

    private val _bookLiveData = MutableLiveData<BookPreviewViewState>()
    val bookLiveData: LiveData<BookPreviewViewState> = _bookLiveData

    private val _bookSeriesLiveData = MutableLiveData<BooksSeriesViewState>()
    val booksSeriesLiveData: LiveData<BooksSeriesViewState> = _bookSeriesLiveData

    private val _relativeBooksLiveData = MutableLiveData<BooksSeriesViewState>()
    val relativeBooksLiveData: LiveData<BooksSeriesViewState> = _relativeBooksLiveData

    private val _progressLiveData = MutableLiveData<LoadingResult<Unit>>()
    val progressLiveData: LiveData<LoadingResult<Unit>> = _progressLiveData

    fun loadBookById(objectId: String) {
        viewModelScope.launch {
            try {
                _progressLiveData.postValue(LoadingResult.Loading)
                _bookLiveData.postValue(bookViewStateMapper(booksRepository.getBookById(objectId)))
                _progressLiveData.postValue(LoadingResult.success())
            } catch (e: Exception) {
                _progressLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }

    fun loadBookSeries(series: String?) {
        series?.let {
            viewModelScope.launch {
                try {
                    _progressLiveData.postValue(LoadingResult.Loading)
                    _bookSeriesLiveData.postValue(
                        bookSeriesViewStateMapper(booksRepository.getBookSeries(series))
                    )
                    _progressLiveData.postValue(LoadingResult.success())
                } catch (e: Exception) {
                    _progressLiveData.postValue(LoadingResult.Error(e))
                }
            }
        }
    }

    fun loadRelativeBooks(genre: String, bookId: Int) {
        viewModelScope.launch {
            try {
                _progressLiveData.postValue(LoadingResult.Loading)
                _relativeBooksLiveData.postValue(
                    bookSeriesViewStateMapper(booksRepository.getRelativeBooks(genre, bookId))
                )
                _progressLiveData.postValue(LoadingResult.success())
            } catch (e: Exception) {
                _progressLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }

    @Parcelize
    data class MyBooksArgs(
        val objectId: String,
        val series: String?,
        val genre: String,
        val bookId: Int
    ) : Parcelable

}