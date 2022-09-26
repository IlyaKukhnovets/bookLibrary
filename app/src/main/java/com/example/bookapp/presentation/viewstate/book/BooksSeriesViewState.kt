package com.example.bookapp.presentation.viewstate.book

data class BooksSeriesViewState(
    val state: List<ViewState>,
    val isShowTitle: Boolean
) {
    data class ViewState(
        val img: String,
        val author: String,
        val bookName: String,
        val objectId: String,
        val series: String,
        val order: Int,
        val id: Int,
        val genre: String
    )
}
