package com.example.bookapp.presentation.viewstate.book

import com.example.bookapp.data.model.book.BookAuthor
import com.example.bookapp.data.model.book.BookPreviewModel
import com.example.bookapp.presentation.viewstate.Mapper
import com.example.bookapp.presentation.viewstate.home.BookStatus
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
            status = mapBookStatus(entity.status),
            genre = entity.genre,
            series = entity.series,
            seriesOrder = entity.seriesOrder,
            authorObject = mapAuthor(entity.authorObject)
        )
    }

    private fun mapBookStatus(status: BookStatus): String {
        return when (status) {
            BookStatus.READ -> "Хочу прочитать"
            BookStatus.FINISHED -> "Прочитал"
            BookStatus.FAVOURITE -> "Избранное"
            BookStatus.NOT_READ -> "Не читал"
        }
    }

    private fun mapAuthor(author: BookAuthor?): BookAuthorViewState? {
        return author?.let {
            BookAuthorViewState(
                id = it.id,
                objectId = it.objectId
            )
        }
    }
}