package com.example.bookapp.presentation.ui.author

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.AuthorsRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.AuthorItemViewState
import com.example.bookapp.presentation.viewstate.AuthorItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorPreviewViewModel @Inject constructor(
    private val authorsRepository: AuthorsRepository,
    private val viewStateMapper: AuthorItemViewStateMapper
) : ViewModel() {

    private val _authorLiveData = MutableLiveData<LoadingResult<List<AuthorItemViewState>>>()
    val authorLiveData: LiveData<LoadingResult<List<AuthorItemViewState>>> = _authorLiveData

    fun loadAuthor(authorId: Int) {
        viewModelScope.launch {
            try {
                _authorLiveData.postValue(LoadingResult.Loading)
                _authorLiveData.postValue(
                    LoadingResult.Success(
                        viewStateMapper(authorsRepository.getBookAuthor(authorId))
                    )
                )
            } catch (e: Exception) {
                _authorLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }
}