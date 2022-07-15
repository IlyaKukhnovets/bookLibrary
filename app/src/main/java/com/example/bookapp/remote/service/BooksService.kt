package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksService {

    companion object {
        private const val SERVICE_PREFIX = "Books"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBooks(): List<BooksResponse>

    @GET("SERVICE_PREFIX?")
    suspend fun getBooksByStatus(
        @Query("where=status") status: Int
    ): List<BooksResponse>

    @GET("SERVICE_PREFIX?")
    suspend fun getBookById(
        @Query("where=status") bookId: Int
    ): List<BooksResponse>
}