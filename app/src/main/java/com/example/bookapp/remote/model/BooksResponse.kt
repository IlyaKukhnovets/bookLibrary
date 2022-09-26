package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "src")
    val src: String?,
    @Json(name = "status")
    val status: Int,
    @Json(name = "objectId")
    val objectId: String,
    @Json(name = "series")
    val series: String?,
    @Json(name = "genre")
    val genre: String
)
