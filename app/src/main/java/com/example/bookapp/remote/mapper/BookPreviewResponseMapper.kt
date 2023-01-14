package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BookAuthor
import com.example.bookapp.data.model.book.BookPreviewModel
import com.example.bookapp.presentation.viewstate.home.BookStatus
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BookAuthorResponse
import com.example.bookapp.remote.model.BookPreviewResponse
import javax.inject.Inject

class BookPreviewResponseMapper @Inject constructor() :
    Mapper<BookPreviewResponse, BookPreviewModel> {
    override fun invoke(response: BookPreviewResponse): BookPreviewModel {
        return BookPreviewModel(
            name = response.name,
            author = response.author,
            image = response.src,
            pagesCount = response.pages,
            bookDescription = response.description,
            status = mapBookStatus(response.status),
            genre = response.genre,
            series = response.series,
            seriesOrder = response.seriesOrder,
            authorObject = mapAuthor(response.authorObject)
        )
    }

    private fun mapAuthor(author: BookAuthorResponse?): BookAuthor? {
        return author?.let {
            BookAuthor(
                id = it.id,
                objectId = it.objectId
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