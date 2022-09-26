package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BookItemModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BooksResponse
import javax.inject.Inject

class BooksResponseMapper @Inject constructor() : Mapper<List<BooksResponse>, List<BookItemModel>> {
    override fun invoke(response: List<BooksResponse>): List<BookItemModel> {
        return response.map { book ->
            BookItemModel(
                id = book.id,
                name = book.name,
                author = book.author,
                image = book.src,
                objectId = book.objectId,
                status = book.status,
                series = book.series,
                genre = book.genre
            )
        }
    }
}