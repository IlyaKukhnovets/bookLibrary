package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BookAuthorItemModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.AuthorsResponse
import javax.inject.Inject

class BookAuthorResponseMapper @Inject constructor() :
    Mapper<AuthorsResponse, BookAuthorItemModel> {
    override fun invoke(response: AuthorsResponse): BookAuthorItemModel {
        return BookAuthorItemModel(
            id = response.id,
            name = response.name,
            src = response.src,
            biography = response.biography,
            genre = response.genre,
            objectId = response.objectId
        )
    }

    fun mapAuthors(response: List<AuthorsResponse>): List<BookAuthorItemModel> {
        return response.map {
            BookAuthorItemModel(
                id = it.id,
                name = it.name,
                src = it.src,
                biography = it.biography,
                genre = it.genre,
                objectId = it.objectId
            )
        }
    }
}