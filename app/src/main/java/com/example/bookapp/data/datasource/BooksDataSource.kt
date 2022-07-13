package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookItemModel

interface BooksDataSource {
    suspend fun getBooksList(): List<BookItemModel>
    suspend fun getBooksByStatus(status: Int): List<BookItemModel>
}