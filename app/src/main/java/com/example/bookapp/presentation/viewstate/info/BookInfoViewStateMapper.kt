package com.example.bookapp.presentation.viewstate.info

import com.example.bookapp.data.model.book.CombinedBooksInfo
import com.example.bookapp.presentation.viewstate.Mapper
import javax.inject.Inject

class BookInfoViewStateMapper @Inject constructor() :
    Mapper<CombinedBooksInfo, List<BooksInfoBaseViewState>> {
    override fun invoke(entity: CombinedBooksInfo): List<BooksInfoBaseViewState> {
        val items = mutableListOf<BooksInfoBaseViewState>()
        items.add(
            BooksInfoBaseViewState.SectionHeader(
                title = "Самые популярные жанры"
            )
        )
        val sortedGenres = entity.genreItems.sortedByDescending { it.count }
        sortedGenres.map {
            items.add(
                BooksInfoBaseViewState.GenresViewState(
                    count = it.count,
                    genre = it.genre!!
                )
            )
        }
        items.add(
            BooksInfoBaseViewState.SectionHeader(
                title = "Любимые авторы"
            )
        )
        val sortedAuthors = entity.authorItems.sortedByDescending { it.count }
        sortedAuthors.map {
            items.add(
                BooksInfoBaseViewState.AuthorsViewState(
                    count = it.count,
                    author = it.author!!
                )
            )
        }
        return items
    }
}