package com.example.bookapp.data.repository

import com.example.bookapp.data.datasource.BooksDataSource
import com.example.bookapp.data.model.BookItemModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksDataSource: BooksDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BooksRepository {
    override suspend fun getBooksList(): List<BookItemModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getBooksList()
        }
    }
}