package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.BookPreviewModel
import com.example.bookapp.remote.Mapper
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
            status = response.status
        )
    }
}