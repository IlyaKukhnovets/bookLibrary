package com.example.bookapp.presentation.viewstate.book

data class BookPreviewViewState(
    val bookName: String,
    val author: String,
    val image: String,
    val pagesCount: Int,
    val bookDescription: String?,
    val status: String,
    val genre: String,
    val seriesOrder: Int?,
    val series: String?,
    val authorObject: BookAuthorViewState?
)

data class BookAuthorViewState(
    val id: Int,
    val objectId: String
)
