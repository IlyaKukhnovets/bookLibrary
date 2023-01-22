package com.example.bookapp.presentation.viewstate.info

sealed class BooksInfoBaseViewState {

    data class GenresViewState(
        val count: Int,
        val genre: String
    ) : BooksInfoBaseViewState()

    data class SectionHeader(
        val title: String
    ) : BooksInfoBaseViewState()

    data class AuthorsViewState(
        val count: Int,
        val author: String,
    ) : BooksInfoBaseViewState()

    data class PagesViewState(
        val title: String,
        val count: Int
    ) : BooksInfoBaseViewState()

}

