package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookPreviewModel
import javax.inject.Inject

class BookPreviewViewStateMapper @Inject constructor() :
    Mapper<BookPreviewModel, BookPreviewViewState> {
    override fun invoke(entity: BookPreviewModel): BookPreviewViewState {
        return BookPreviewViewState(
            bookName = entity.name,
            author = entity.author,
            image = entity.image,
            pagesCount = entity.pagesCount ?: 0,
            bookDescription = entity.bookDescription,
            status = mapBookStatus(entity.status)
        )
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