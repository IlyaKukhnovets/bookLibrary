package com.example.bookapp.presentation.viewstate

import com.example.bookapp.data.model.BookItemModel
import javax.inject.Inject

class BookItemViewStateMapper @Inject constructor() : Mapper<List<BookItemModel>, List<BookItemTopViewState>> {
    override fun invoke(entity: List<BookItemModel>): List<BookItemTopViewState> {
        return entity.map {
            BookItemTopViewState(
                name = it.name
            )
        }
    }
}