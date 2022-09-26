package com.example.bookapp.presentation.viewstate.home

data class BookItemViewState(
    val id: Int,
    val name: String,
    val author: String,
    val image: String?,
    val objectId: String,
    val status: BookStatus,
    val series: String?,
    val genre: String
)

enum class BookStatus(val status: Int) {
    NOT_READ(0),
    FAVOURITE(1),
    READ(2),
    FINISHED(3)
}
