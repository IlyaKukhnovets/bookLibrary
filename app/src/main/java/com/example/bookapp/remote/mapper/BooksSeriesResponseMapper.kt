package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BooksSeriesResponse
import javax.inject.Inject

class BooksSeriesResponseMapper @Inject constructor() :
    Mapper<List<BooksSeriesResponse>, List<BooksSeriesModel>> {
    override fun invoke(response: List<BooksSeriesResponse>): List<BooksSeriesModel> {
        return response.map {
            BooksSeriesModel(
                img = it.src,
                author = it.author,
                bookName = it.name,
                objectId = it.objectId,
                series = it.series,
                id = it.id,
                order = it.seriesOrder,
                genre = it.genre
            )
        }
    }
}