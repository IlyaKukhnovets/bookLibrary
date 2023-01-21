package com.example.bookapp.presentation.viewstate.info

import com.example.bookapp.data.model.book.CombinedBooksInfo
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class BookInfoViewStateMapper @Inject constructor() :
    Mapper<CombinedBooksInfo, List<BookInfoViewState>> {
    override fun invoke(entity: CombinedBooksInfo): List<BookInfoViewState> {
        val genreItems = mutableListOf<BookInfoViewState>()
        entity.genreItems.map {
            genreItems.add(
                BookInfoViewState(
                    count = it.count,
                    genre = it.genre
                )
            )
        }
        return genreItems
    }

}