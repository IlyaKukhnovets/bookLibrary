package com.example.bookapp.presentation.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.info.BookInfoViewStateMapper
import com.example.bookapp.presentation.viewstate.info.BooksInfoBaseViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksInfoViewModel @Inject constructor(
    private val repository: BooksRepository,
    private val mapper: BookInfoViewStateMapper
) : ViewModel() {

    private val _booksInfoLiveData = MutableLiveData<LoadingResult<List<BooksInfoBaseViewState>>>()
    val booksInfoLiveData: LiveData<LoadingResult<List<BooksInfoBaseViewState>>> = _booksInfoLiveData

    fun init() {
        viewModelScope.launch {
            repository.getBooksInfo()
                .onStart {
                    _booksInfoLiveData.postValue(LoadingResult.Loading)
                }
                .catch {
                    _booksInfoLiveData.postValue(LoadingResult.Error(it as Exception))
                }
                .collectLatest { items ->
                    _booksInfoLiveData.postValue(LoadingResult.Success(mapper(items)))
                }
        }
    }

}