package com.example.bookapp.data.model.book

data class BooksSeriesModel(
    val img: String,
    val author: String,
    val bookName: String,
    val objectId: String,
    val series: String?,
    val order: Int?,
    val id: Int
)
