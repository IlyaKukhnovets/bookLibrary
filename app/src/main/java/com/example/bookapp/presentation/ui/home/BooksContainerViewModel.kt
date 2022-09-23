package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.AuthorsRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewState
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksContainerViewModel @Inject constructor(
    private val authorsRepository: AuthorsRepository,
    private val viewStateMapper: AuthorItemViewStateMapper
) : ViewModel() {

    private val _authorsLiveData = MutableLiveData<LoadingResult<List<AuthorItemViewState>>>()
    val authorsLiveData: LiveData<LoadingResult<List<AuthorItemViewState>>> = _authorsLiveData

    init {
        viewModelScope.launch {
            _authorsLiveData.postValue(LoadingResult.Loading)
            try {
                _authorsLiveData.postValue(
                    LoadingResult.Success(viewStateMapper.mapAuthors(authorsRepository.getBookAuthors()))
                )
            } catch (e: Exception) {
                _authorsLiveData.postValue(LoadingResult.Error(e))
            }

        }
    }

}