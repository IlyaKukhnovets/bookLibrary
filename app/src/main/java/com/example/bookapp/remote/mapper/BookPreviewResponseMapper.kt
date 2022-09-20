package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.BookPreviewModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BookPreviewResponse
import javax.inject.Inject

class BookPreviewResponseMapper @Inject constructor() :
    Mapper<List<BookPreviewResponse>, List<BookPreviewModel>> {
    override fun invoke(response: List<BookPreviewResponse>): List<BookPreviewModel> {
        return response.map { book ->
            BookPreviewModel(
                name = book.name,
                author = book.author,
                image = book.src,
                pagesCount = book.pages,
                bookDescription = book.description,
                status = book.status
            )
        }
    }
}