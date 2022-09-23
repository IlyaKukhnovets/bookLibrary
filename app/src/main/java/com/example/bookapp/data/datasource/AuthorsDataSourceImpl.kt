package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.data.model.book.BookAuthorItemModel
import com.example.bookapp.remote.mapper.AuthorRelativeResponseMapper
import com.example.bookapp.remote.mapper.BookAuthorResponseMapper
import com.example.bookapp.remote.service.AuthorsService
import javax.inject.Inject

class AuthorsDataSourceImpl @Inject constructor(
    private val service: AuthorsService,
    private val booksAuthorsMapper: BookAuthorResponseMapper,
    private val authorsRelativeMapper: AuthorRelativeResponseMapper
) : AuthorsDataSource {

    override suspend fun getBookAuthors(): List<BookAuthorItemModel> {
        return booksAuthorsMapper.mapAuthors(service.getBookAuthors())
    }

    override suspend fun getBookAuthor(id: String): BookAuthorItemModel {
        return booksAuthorsMapper(service.getBookAuthor(id))
    }

    override suspend fun getAuthorsRelative(genre: String, id: Int): List<AuthorRelativeModel> {
        return authorsRelativeMapper(service.getRelativeAuthors("genre='$genre' and id!=$id"))
    }
}