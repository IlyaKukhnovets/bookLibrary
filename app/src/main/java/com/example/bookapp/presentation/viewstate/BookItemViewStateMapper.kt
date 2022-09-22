package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookItemModel
import javax.inject.Inject

class BookItemViewStateMapper @Inject constructor() : Mapper<BookItemModel, BookItemViewState> {
    override fun invoke(entity: BookItemModel): BookItemViewState {
        return BookItemViewState(
            id = entity.id,
            name = entity.name,
            author = entity.author,
            image = entity.image,
            status = mapBookStatus(entity.status)
        )
    }
}

private fun mapBookStatus(status: Int): BookStatus {
    return when (status) {
        1 -> BookStatus.FAVOURITE
        2 -> BookStatus.READ
        3 -> BookStatus.FINISHED
        else -> BookStatus.NOT_READ
    }
}
