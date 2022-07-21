package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookAuthorItemModel
import javax.inject.Inject

class AuthorItemViewStateMapper @Inject constructor() :
    Mapper<List<BookAuthorItemModel>, List<AuthorItemViewState>> {
    override fun invoke(entity: List<BookAuthorItemModel>): List<AuthorItemViewState> {
        return entity.map {
            AuthorItemViewState(
                id = it.id,
                image = it.src,
                name = it.name,
                biography = it.biography
            )
        }
    }
}