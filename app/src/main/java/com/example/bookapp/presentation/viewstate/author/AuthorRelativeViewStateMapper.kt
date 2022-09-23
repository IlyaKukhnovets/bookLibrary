package com.example.bookapp.presentation.viewstate.author

import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class AuthorRelativeViewStateMapper @Inject constructor() :
    Mapper<List<AuthorRelativeModel>, List<AuthorRelativeViewState>> {
    override fun invoke(entity: List<AuthorRelativeModel>): List<AuthorRelativeViewState> {
        return entity.map { author ->
            AuthorRelativeViewState(
                img = author.img,
                genre = author.genre,
                objectId = author.objectId,
                id = author.id,
                name = author.name
            )
        }
    }
}