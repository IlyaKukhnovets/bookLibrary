package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorRelativeResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "genre")
    val genre: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "src")
    val src: String,
    @Json(name = "objectId")
    val objectId: String
)