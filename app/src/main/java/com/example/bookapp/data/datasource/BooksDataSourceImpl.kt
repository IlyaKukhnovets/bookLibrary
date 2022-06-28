package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.remote.mapper.BooksResponseMapper
import com.example.bookapp.remote.service.BooksService
import javax.inject.Inject

class BooksDataSourceImpl @Inject constructor(
    private val service: BooksService,
    private val mapper: BooksResponseMapper
) : BooksDataSource {
    override suspend fun getBooksList(): List<BookItemModel> {
        return mapper(service.getBooks())
    }
}