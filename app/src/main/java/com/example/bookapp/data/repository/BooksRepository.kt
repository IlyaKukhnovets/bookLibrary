package com.example.bookapp.data.repository

import androidx.paging.PagingSource
import com.example.bookapp.data.model.book.*
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooksList(status: Int? = null): PagingSource<Int, BookItemModel>
    fun getBooksListWithArgs(argument: String): PagingSource<Int, BookItemModel>
    suspend fun getBookById(bookId: String): BookPreviewModel
    suspend fun getBookSeries(series: String): List<BooksSeriesModel>
    suspend fun getAuthorBooks(authorName: String): List<BooksSeriesModel>
    suspend fun getRelativeBooks(genre: String, bookId: Int): List<BooksSeriesModel>
    suspend fun getBooksInfo(): Flow<CombinedBooksInfo>
}