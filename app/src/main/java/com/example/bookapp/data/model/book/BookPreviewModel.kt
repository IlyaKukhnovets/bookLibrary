package com.example.bookapp.data.model.book

import com.example.bookapp.presentation.viewstate.home.BookStatus

data class BookPreviewModel(
    val name: String,
    val author: String,
    val image: String,
    val pagesCount: Int?,
    val bookDescription: String?,
    val status: BookStatus,
    val genre: String,
    val seriesOrder: Int?,
    val series: String?,
    val authorObject: BookAuthor?
)

data class BookAuthor(
    val id: Int,
    val objectId: String
)
