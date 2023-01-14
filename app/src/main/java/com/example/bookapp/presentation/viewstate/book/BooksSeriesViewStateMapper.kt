package com.example.bookapp.presentation.viewstate.book

import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class BooksSeriesViewStateMapper @Inject constructor() :
    Mapper<List<BooksSeriesModel>, BooksSeriesViewState> {
    override fun invoke(entity: List<BooksSeriesModel>): BooksSeriesViewState {
        return BooksSeriesViewState(
            isShowTitle = entity.isNotEmpty(),
            isShowArrowButton = entity.size > 2,
            authorBooksCount = entity.size,
            state = entity.map {
                BooksSeriesViewState.ViewState(
                    img = it.img,
                    author = it.author,
                    bookName = it.bookName,
                    objectId = it.objectId,
                    series = it.series ?: "",
                    id = it.id,
                    order = it.order ?: 0,
                    genre = it.genre,
                    isShowOrder = it.isShowOrder
                )
            }
        )
    }
}