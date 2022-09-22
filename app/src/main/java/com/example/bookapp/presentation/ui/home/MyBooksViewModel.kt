package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.viewstate.BookItemViewState
import com.example.bookapp.presentation.viewstate.BookItemViewStateMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val viewStateMapper: BookItemViewStateMapper
) : ViewModel() {

    private lateinit var flow: Flow<PagingData<BookItemViewState>>

    fun init() {
        flow = createFlow()
    }

    fun getFlow() = flow

    private fun createFlow(): Flow<PagingData<BookItemViewState>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ) { booksRepository.getBooksList() }
            .flow
            .map { pagingData ->
                pagingData.map { viewStateMapper(it) }
            }
    }

}