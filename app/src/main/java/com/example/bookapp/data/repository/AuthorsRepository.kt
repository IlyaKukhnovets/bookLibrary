package com.example.bookapp.data.repository

import com.example.bookapp.data.model.BookAuthorItemModel

interface AuthorsRepository {
    suspend fun getBookAuthors(): List<BookAuthorItemModel>
    suspend fun getBookAuthor(id: Int): List<BookAuthorItemModel>
}