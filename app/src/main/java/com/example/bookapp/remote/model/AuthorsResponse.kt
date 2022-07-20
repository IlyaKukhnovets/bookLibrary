package com.example.bookapp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorsResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "src")
    val src: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "biography")
    val biography: String?
)
