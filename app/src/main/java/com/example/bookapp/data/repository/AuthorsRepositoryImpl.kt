package com.example.bookapp.data.repository

import com.example.bookapp.data.datasource.AuthorsDataSource
import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.data.model.book.BookAuthorItemModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthorsRepositoryImpl @Inject constructor(
    private val authorsDataSource: AuthorsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthorsRepository {
    override suspend fun getBookAuthors(): List<BookAuthorItemModel> {
        return withContext(ioDispatcher) {
            authorsDataSource.getBookAuthors()
        }
    }

    override suspend fun getBookAuthor(id: String): BookAuthorItemModel {
        return withContext(ioDispatcher) {
            authorsDataSource.getBookAuthor(id)
        }
    }

    override suspend fun getAuthorsRelative(genre: String, id: Int): List<AuthorRelativeModel> {
        return withContext(ioDispatcher) {
            authorsDataSource.getAuthorsRelative(genre, id)
        }
    }
}