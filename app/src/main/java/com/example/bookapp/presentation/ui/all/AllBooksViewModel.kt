package com.example.bookapp.presentation.ui.all

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.viewstate.home.BookItemViewState
import com.example.bookapp.presentation.viewstate.home.BookItemViewStateMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AllBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val viewStateMapper: BookItemViewStateMapper
) : ViewModel() {

    private lateinit var flow: Flow<PagingData<BookItemViewState>>

    fun init(author: String) {
        flow = createFlow(author)
    }

    fun getFlow() = flow

    private fun createFlow(author: String): Flow<PagingData<BookItemViewState>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ) { booksRepository.getBooksListWithArgs(author) }
            .flow
            .map { pagingData ->
                pagingData.map { viewStateMapper(it) }
            }
    }
}