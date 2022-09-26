package com.example.bookapp.presentation.viewstate.author

data class AuthorRelativeViewState(
    val state: List<ViewState>,
    val isShowTitle: Boolean,
) {
    data class ViewState(
        val img: String,
        val name: String,
        val genre: String,
        val objectId: String,
        val id: Int
    )
}