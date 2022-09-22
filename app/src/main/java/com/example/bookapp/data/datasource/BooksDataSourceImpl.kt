package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.data.model.BookPreviewModel
import com.example.bookapp.presentation.viewstate.BookStatus
import com.example.bookapp.remote.mapper.BookPreviewResponseMapper
import com.example.bookapp.remote.mapper.BooksResponseMapper
import com.example.bookapp.remote.service.BooksService
import javax.inject.Inject

class BooksDataSourceImpl @Inject constructor(
    private val service: BooksService,
    private val booksMapper: BooksResponseMapper,
    private val bookPreviewMapper: BookPreviewResponseMapper
) : BooksDataSource {
    override suspend fun getBooksList(limit: Int, offset: Int, status: Int?): List<BookItemModel> {
        return if (status != null) {
            booksMapper(service.getBooks("$limit","$offset","status=$status"))
        } else {
            booksMapper(service.getBooks("$limit","$offset","status!=${BookStatus.FAVOURITE.status}"))
        }
    }

    override suspend fun getBookById(bookId: Int): List<BookPreviewModel> {
        return bookPreviewMapper(service.getBookById("id=$bookId"))
    }
}