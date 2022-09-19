package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.AuthorsRepository
import com.example.bookapp.presentation.viewstate.AuthorItemViewState
import com.example.bookapp.presentation.viewstate.AuthorItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksContainerViewModel @Inject constructor(
    private val authorsRepository: AuthorsRepository,
    private val viewStateMapper: AuthorItemViewStateMapper
) : ViewModel() {

    private val _authorsLiveData = MutableLiveData<List<AuthorItemViewState>>()
    val authorsLiveData: LiveData<List<AuthorItemViewState>> = _authorsLiveData

    init {
        viewModelScope.launch {
            _authorsLiveData.postValue(viewStateMapper(authorsRepository.getBookAuthors()))
        }
    }

}