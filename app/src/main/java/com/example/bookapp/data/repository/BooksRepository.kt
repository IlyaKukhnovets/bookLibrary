package com.example.bookapp.data.repository

import com.example.bookapp.data.model.BookItemModel

interface BooksRepository {
    suspend fun getBooksList(): List<BookItemModel>
}