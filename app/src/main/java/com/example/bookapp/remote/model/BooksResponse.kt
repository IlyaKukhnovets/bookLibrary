package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "src")
    val src: String?,
    @Json(name = "status")
    val status: Int
)
