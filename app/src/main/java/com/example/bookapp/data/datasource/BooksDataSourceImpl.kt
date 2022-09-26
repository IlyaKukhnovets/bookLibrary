package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.book.BookItemModel
import com.example.bookapp.data.model.book.BookPreviewModel
import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.presentation.viewstate.home.BookStatus
import com.example.bookapp.remote.mapper.BookPreviewResponseMapper
import com.example.bookapp.remote.mapper.BooksResponseMapper
import com.example.bookapp.remote.mapper.BooksSeriesResponseMapper
import com.example.bookapp.remote.service.BooksService
import javax.inject.Inject

class BooksDataSourceImpl @Inject constructor(
    private val service: BooksService,
    private val booksMapper: BooksResponseMapper,
    private val bookPreviewMapper: BookPreviewResponseMapper,
    private val booksSeriesResponseMapper: BooksSeriesResponseMapper
) : BooksDataSource {
    override suspend fun getBooksList(limit: Int, offset: Int, status: Int?): List<BookItemModel> {
        return if (status != null) {
            booksMapper(service.getBooks("$limit","$offset","status=$status"))
        } else {
            booksMapper(service.getBooks("$limit","$offset","status!=${BookStatus.FAVOURITE.status}"))
        }
    }

    override suspend fun getBookById(bookId: String): BookPreviewModel {
        return bookPreviewMapper(service.getBookById(bookId))
    }

    override suspend fun getBookSeries(series: String): List<BooksSeriesModel> {
        return booksSeriesResponseMapper(service.getBooksWithArgs("series='$series'"))
    }

    override suspend fun getAuthorBooks(authorName: String): List<BooksSeriesModel> {
        return booksSeriesResponseMapper(service.getBooksWithArgs("author='$authorName'"))
    }

    override suspend fun getRelativeBooks(genre: String, bookId: Int): List<BooksSeriesModel> {
        return booksSeriesResponseMapper(service.getBooksWithArgs("genre='$genre' and id!=$bookId"))
    }

}