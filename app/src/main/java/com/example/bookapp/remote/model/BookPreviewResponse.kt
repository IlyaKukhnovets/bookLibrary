package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookPreviewResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "src")
    val src: String?,
    @Json(name = "pages")
    val pages: Int?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "status")
    val status: Int
)
