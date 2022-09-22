package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.BookPreviewResponse
import com.example.bookapp.remote.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    companion object {
        private const val SERVICE_PREFIX = "Book"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBooks(
        @Query("pageSize") limit: String,
        @Query("offset") offset: String,
        @Query("where") status: String
    ): List<BooksResponse>

    @GET("$SERVICE_PREFIX?")
    suspend fun getBookById(
        @Query("where") bookId: String
    ): List<BookPreviewResponse>
}