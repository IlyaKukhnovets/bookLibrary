package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.BookAuthorItemModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.AuthorsResponse
import javax.inject.Inject

class BookAuthorResponseMapper @Inject constructor() :
    Mapper<List<AuthorsResponse>, List<BookAuthorItemModel>> {
    override fun invoke(response: List<AuthorsResponse>): List<BookAuthorItemModel> {
        return response.map {
            BookAuthorItemModel(
                id = it.id,
                name = it.name,
                src = it.src,
                biography = it.biography
            )
        }
    }
}