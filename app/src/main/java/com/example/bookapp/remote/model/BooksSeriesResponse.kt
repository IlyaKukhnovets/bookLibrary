package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksSeriesResponse(
    @Json(name = "src")
    val src: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "series")
    val series: String?,
    @Json(name = "series_order")
    val seriesOrder: Int?,
    @Json(name = "objectId")
    val objectId: String,
    @Json(name = "genre")
    val genre: String
)
