package com.example.bookapp.presentation.viewstate.author

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorPreviewArgs(
    val objectId: String,
    val genre: String,
    val authorId: Int,
    val author: String
) : Parcelable
