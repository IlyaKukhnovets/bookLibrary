package com.example.bookapp.presentation.ui.author

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.repository.AuthorsRepository
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.presentation.viewstate.author.AuthorRelativeViewState
import com.example.bookapp.presentation.viewstate.author.AuthorRelativeViewStateMapper
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewState
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewStateMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorPreviewViewModel @Inject constructor(
    private val authorsRepository: AuthorsRepository,
    private val authorsViewStateMapper: AuthorItemViewStateMapper,
    private val authorsRelativeViewStateMapper: AuthorRelativeViewStateMapper
) : ViewModel() {

    private val _authorLiveData = MutableLiveData<AuthorItemViewState>()
    val authorLiveData: LiveData<AuthorItemViewState> = _authorLiveData

    private val _progressLiveData = MutableLiveData<LoadingResult<Unit>>()
    val progressLiveData: LiveData<LoadingResult<Unit>> = _progressLiveData

    private val _relativeAuthorsLiveData = MutableLiveData<List<AuthorRelativeViewState>>()
    val relativeAuthorsLiveData: LiveData<List<AuthorRelativeViewState>> = _relativeAuthorsLiveData

    fun loadAuthor(authorId: String) {
        viewModelScope.launch {
            try {
                _progressLiveData.postValue(LoadingResult.Loading)
                _authorLiveData.postValue(
                    authorsViewStateMapper(authorsRepository.getBookAuthor(authorId))
                )
                _progressLiveData.postValue(LoadingResult.success())
            } catch (e: Exception) {
                _progressLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }

    fun loadRelativeAuthors(genre: String, id: Int) {
        viewModelScope.launch {
            try {
                _progressLiveData.postValue(LoadingResult.Loading)
                _relativeAuthorsLiveData.postValue(
                    authorsRelativeViewStateMapper(authorsRepository.getAuthorsRelative(genre, id))
                )
                _progressLiveData.postValue(LoadingResult.success())
            } catch (e: Exception) {
                _progressLiveData.postValue(LoadingResult.Error(e))
            }
        }
    }

    fun isShowAuthorsHeader(): Boolean = !_relativeAuthorsLiveData.value.isNullOrEmpty()

}