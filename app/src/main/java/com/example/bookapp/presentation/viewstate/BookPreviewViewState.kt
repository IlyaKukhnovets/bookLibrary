package com.example.bookapp.presentation.viewstate

data class BookPreviewViewState(
    val bookName: String,
    val author: String,
    val image: String?,
    val pagesCount: Int,
    val bookDescription: String?,
    val status: BookStatus
)
