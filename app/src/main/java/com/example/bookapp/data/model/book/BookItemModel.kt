package com.example.bookapp.data.model.book

data class BookItemModel(
    val id: Int,
    val name: String,
    val author: String,
    val image: String?,
    val status: Int,
    val objectId: String,
    val series: String?,
    val genre: String
)
