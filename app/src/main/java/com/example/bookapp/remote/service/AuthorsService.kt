package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.AuthorRelativeResponse
import com.example.bookapp.remote.model.AuthorsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthorsService {

    companion object {
        private const val SERVICE_PREFIX = "Authors"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBookAuthors(): List<AuthorsResponse>

    @GET("$SERVICE_PREFIX/{objectId}")
    suspend fun getBookAuthor(
        @Path("objectId") objectId: String
    ): AuthorsResponse

    @GET(SERVICE_PREFIX)
    suspend fun getRelativeAuthors(
        @Query("where") genre: String
    ): List<AuthorRelativeResponse>
}