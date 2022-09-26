package com.example.bookapp.presentation.viewstate.author

import com.example.bookapp.data.model.author.AuthorRelativeModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class AuthorRelativeViewStateMapper @Inject constructor() :
    Mapper<List<AuthorRelativeModel>, AuthorRelativeViewState> {
    override fun invoke(entity: List<AuthorRelativeModel>): AuthorRelativeViewState {
        return AuthorRelativeViewState(
            state = entity.map {
                AuthorRelativeViewState.ViewState(
                    img = it.img,
                    genre = it.genre,
                    objectId = it.objectId,
                    id = it.id,
                    name = it.name
                )
            },
            isShowTitle = entity.isNotEmpty()
        )
    }
}