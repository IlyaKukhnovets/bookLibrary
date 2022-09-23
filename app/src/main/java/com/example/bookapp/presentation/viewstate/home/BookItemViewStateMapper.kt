package com.example.bookapp.presentation.viewstate.home

import com.example.bookapp.data.model.book.BookItemModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class BookItemViewStateMapper @Inject constructor() : Mapper<BookItemModel, BookItemViewState> {
    override fun invoke(entity: BookItemModel): BookItemViewState {
        return BookItemViewState(
            id = entity.id,
            name = entity.name,
            author = entity.author,
            objectId = entity.objectId,
            image = entity.image,
            status = mapBookStatus(entity.status),
            series = entity.series
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
