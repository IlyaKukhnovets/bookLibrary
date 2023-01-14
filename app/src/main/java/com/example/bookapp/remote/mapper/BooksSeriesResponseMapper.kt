package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BooksSeriesResponse
import javax.inject.Inject

class BooksSeriesResponseMapper @Inject constructor() :
    Mapper<Pair<List<BooksSeriesResponse>,Boolean>, List<BooksSeriesModel>> {
    override fun invoke(response: Pair<List<BooksSeriesResponse>,Boolean>): List<BooksSeriesModel> {
        return response.first.map {
            BooksSeriesModel(
                img = it.src,
                author = it.author,
                bookName = it.name,
                objectId = it.objectId,
                series = it.series,
                id = it.id,
                order = it.seriesOrder,
                genre = it.genre,
                isShowOrder = response.second
            )
        }
    }
}