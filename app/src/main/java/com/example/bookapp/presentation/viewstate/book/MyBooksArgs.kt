package com.example.bookapp.presentation.viewstate.book

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyBooksArgs(
    val objectId: String,
    val series: String?,
    val genre: String,
    val bookId: Int
) : Parcelable