package com.example.bookapp.presentation.viewstate

data class BookItemViewState(
    val name: String,
    val author: String,
    val image: String?,
    val status: BookStatus
)

enum class BookStatus(val status: Int) {
    NOT_READ(0),
    FAVOURITE(1),
    READ(2),
    FINISHED(3)
}
