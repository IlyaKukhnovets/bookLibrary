package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.AuthorsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorsService {

    companion object {
        private const val SERVICE_PREFIX = "Authors"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBookAuthors(): List<AuthorsResponse>

    @GET(SERVICE_PREFIX)
    suspend fun getBookAuthor(
        @Query("where") authorId: String
    ): List<AuthorsResponse>
}