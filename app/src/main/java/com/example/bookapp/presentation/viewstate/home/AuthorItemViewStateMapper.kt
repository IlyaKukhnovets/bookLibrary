package com.example.bookapp.presentation.viewstate.home

import com.example.bookapp.data.model.book.BookAuthorItemModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class AuthorItemViewStateMapper @Inject constructor() :
    Mapper<BookAuthorItemModel, AuthorItemViewState> {
    override fun invoke(entity: BookAuthorItemModel): AuthorItemViewState {
        return AuthorItemViewState(
            id = entity.id,
            image = entity.src,
            name = entity.name,
            biography = entity.biography,
            genre = entity.genre,
            objectId = entity.objectId
        )
    }

    fun mapAuthors(entity: List<BookAuthorItemModel>): List<AuthorItemViewState> {
        return entity.map {
            AuthorItemViewState(
                id = it.id,
                name = it.name,
                image = it.src,
                biography = it.biography,
                genre = it.genre,
                objectId = it.objectId
            )
        }
    }

}