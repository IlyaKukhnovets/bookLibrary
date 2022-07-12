package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookItemModel
import javax.inject.Inject

class BookItemViewStateMapper @Inject constructor() : Mapper<List<BookItemModel>, List<BookItemViewState>> {
    override fun invoke(entity: List<BookItemModel>): List<BookItemViewState> {
        return entity.map { book ->
            BookItemViewState(
                name = book.name,
                author = book.author,
                image = book.image,
                status = book.status
            )
        }
    }
}