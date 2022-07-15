package com.example.bookapp.data.repository

import com.example.bookapp.data.model.BookItemModel

interface BooksRepository {
    suspend fun getBooksList(): List<BookItemModel>
    suspend fun getBooksByStatus(status: Int): List<BookItemModel>
    suspend fun getBookById(bookId: Int): List<BookItemModel>
}