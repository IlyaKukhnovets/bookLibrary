package com.example.bookapp.data.model

data class BookPreviewModel(
    val name: String,
    val author: String,
    val image: String?,
    val pagesCount: Int?,
    val bookDescription: String?,
    val status: Int
)
