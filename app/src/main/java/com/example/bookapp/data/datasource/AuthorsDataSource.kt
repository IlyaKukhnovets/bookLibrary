package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.data.model.book.BookAuthorItemModel

interface AuthorsDataSource {
    suspend fun getBookAuthors(): List<BookAuthorItemModel>
    suspend fun getBookAuthor(id: String): BookAuthorItemModel
    suspend fun getAuthorsRelative(genre: String, id: Int): List<AuthorRelativeModel>
}