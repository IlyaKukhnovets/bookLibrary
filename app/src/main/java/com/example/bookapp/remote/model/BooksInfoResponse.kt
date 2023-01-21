package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksInfoResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "author")
    val author: String?,
    @Json(name = "genre")
    val genre: String?
)
