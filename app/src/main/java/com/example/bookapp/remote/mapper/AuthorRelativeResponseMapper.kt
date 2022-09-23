package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.AuthorRelativeResponse
import javax.inject.Inject

class AuthorRelativeResponseMapper @Inject constructor() :
    Mapper<List<AuthorRelativeResponse>, List<AuthorRelativeModel>> {
    override fun invoke(response: List<AuthorRelativeResponse>): List<AuthorRelativeModel> {
        return response.map { author ->
            AuthorRelativeModel(
                img = author.src,
                genre = author.genre,
                objectId = author.objectId,
                id = author.id,
                name = author.name
            )
        }
    }
}