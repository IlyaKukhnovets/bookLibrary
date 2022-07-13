package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookItemModel
import javax.inject.Inject

class BookItemViewStateMapper @Inject constructor() :
    Mapper<List<BookItemModel>, List<BookItemViewState>> {
    override fun invoke(entity: List<BookItemModel>): List<BookItemViewState> {
        return entity.map { book ->
            BookItemViewState(
                name = book.name,
                author = book.author,
                image = book.image,
                status = mapBookStatus(book.status)
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
}