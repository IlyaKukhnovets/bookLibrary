package com.example.bookapp.data.model.book

data class BookPreviewModel(
    val name: String,
    val author: String,
    val image: String,
    val pagesCount: Int?,
    val bookDescription: String?,
    val status: Int,
    val genre: String,
    val seriesOrder: Int?,
    val series: String?
)
