package com.example.bookapp.presentation.viewstate.home

data class AuthorItemViewState(
    val id: Int,
    val name: String,
    val image: String?,
    val biography: String?,
    val genre: String,
    val objectId: String
)
