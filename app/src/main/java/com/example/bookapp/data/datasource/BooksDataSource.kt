package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.book.BookItemModel
import com.example.bookapp.data.model.book.BookPreviewModel
import com.example.bookapp.data.model.book.BooksSeriesModel

interface BooksDataSource {
    suspend fun getBooksList(limit: Int, offset: Int, status: Int?): List<BookItemModel>
    suspend fun getBookById(bookId: String): BookPreviewModel
    suspend fun getBookSeries(series: String): List<BooksSeriesModel>
    suspend fun getAuthorBooks(authorName: String): List<BooksSeriesModel>
    suspend fun getRelativeBooks(genre: String, bookId: Int): List<BooksSeriesModel>
}