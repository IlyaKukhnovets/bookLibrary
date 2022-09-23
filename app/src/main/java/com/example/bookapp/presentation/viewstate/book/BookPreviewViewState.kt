package com.example.bookapp.presentation.viewstate.book

import com.example.bookapp.presentation.viewstate.home.BookStatus

data class BookPreviewViewState(
    val bookName: String,
    val author: String,
    val image: String?,
    val pagesCount: Int,
    val bookDescription: String?,
    val status: BookStatus,
    val genre: String,
    val seriesOrder: Int?,
    val series: String?
)
