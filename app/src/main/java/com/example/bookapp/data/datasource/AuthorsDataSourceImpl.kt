package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookAuthorItemModel
import com.example.bookapp.remote.mapper.BookAuthorResponseMapper
import com.example.bookapp.remote.service.AuthorsService
import javax.inject.Inject

class AuthorsDataSourceImpl @Inject constructor(
    private val service: AuthorsService,
    private val mapper: BookAuthorResponseMapper
) : AuthorsDataSource {

    override suspend fun getBookAuthors(): List<BookAuthorItemModel> {
        return mapper(service.getBookAuthors())
    }
}