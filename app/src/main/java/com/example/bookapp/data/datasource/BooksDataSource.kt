package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.data.model.BookPreviewModel

interface BooksDataSource {
    suspend fun getBooksList(limit: Int, offset: Int, status: Int?): List<BookItemModel>
    suspend fun getBookById(bookId: Int): List<BookPreviewModel>
}