package com.example.bookapp.presentation.viewstate.book

import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class BooksSeriesViewStateMapper @Inject constructor() : Mapper<List<BooksSeriesModel>, List<BooksSeriesViewState>> {
    override fun invoke(entity: List<BooksSeriesModel>): List<BooksSeriesViewState> {
        return entity.map {
            BooksSeriesViewState(
                img = it.img,
                author = it.author,
                bookName = it.bookName,
                objectId = it.objectId,
                series = it.series ?: "",
                id = it.id,
                order = it.order ?: 0
            )
        }
    }

}