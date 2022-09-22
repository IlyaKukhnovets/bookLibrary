package com.example.bookapp.data.repository

import androidx.paging.PagingSource
import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.data.model.BookPreviewModel

interface BooksRepository {
    fun getBooksList(status: Int? = null): PagingSource<Int, BookItemModel>
    suspend fun getBookById(bookId: Int): List<BookPreviewModel>
}