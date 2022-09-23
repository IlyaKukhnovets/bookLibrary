package com.example.bookapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.viewstate.home.BookItemViewState
import com.example.bookapp.presentation.viewstate.home.BookItemViewStateMapper
import com.example.bookapp.presentation.viewstate.home.BookStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyFavouriteBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val mapper: BookItemViewStateMapper
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
        ) { booksRepository.getBooksList(BookStatus.FAVOURITE.status) }
            .flow
            .map { books -> books.map { mapper(it) } }
    }

}