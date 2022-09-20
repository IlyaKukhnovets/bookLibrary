package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.data.model.BookPreviewModel
import com.example.bookapp.remote.mapper.BookPreviewResponseMapper
import com.example.bookapp.remote.mapper.BooksResponseMapper
import com.example.bookapp.remote.service.BooksService
import javax.inject.Inject

class BooksDataSourceImpl @Inject constructor(
    private val service: BooksService,
    private val booksMapper: BooksResponseMapper,
    private val bookPreviewMapper: BookPreviewResponseMapper
) : BooksDataSource {
    override suspend fun getBooksList(): List<BookItemModel> {
        return booksMapper(service.getBooks())
    }

    override suspend fun getBooksByStatus(status: Int): List<BookItemModel> {
        return booksMapper(service.getBooksByStatus("status=$status"))
    }

    override suspend fun getBookById(bookId: Int): List<BookPreviewModel> {
        return bookPreviewMapper(service.getBookById("id=$bookId"))
    }
}