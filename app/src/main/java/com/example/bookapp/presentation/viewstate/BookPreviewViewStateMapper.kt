package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookPreviewModel
import javax.inject.Inject

class BookPreviewViewStateMapper @Inject constructor() :
    Mapper<List<BookPreviewModel>, List<BookPreviewViewState>> {
    override fun invoke(entity: List<BookPreviewModel>): List<BookPreviewViewState> {
        return entity.map { book ->
            BookPreviewViewState(
                bookName = book.name,
                author = book.author,
                image = book.image,
                pagesCount = book.pagesCount ?: 0,
                bookDescription = book.bookDescription,
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