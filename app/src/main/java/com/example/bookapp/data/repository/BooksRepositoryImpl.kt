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

    override suspend fun getBooksByStatus(status: Int): List<BookItemModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getBooksByStatus(status)
        }
    }

    override suspend fun getBookById(bookId: Int): List<BookItemModel> {
        return withContext(ioDispatcher){
            booksDataSource.getBookById(bookId)
        }
    }
}