package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.BookPreviewResponse
import com.example.bookapp.remote.model.BooksResponse
import com.example.bookapp.remote.model.BooksSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("$SERVICE_PREFIX/{objectId}")
    suspend fun getBookById(
        @Path("objectId") objectId: String,
        @Query("loadRelations") args: String
    ): BookPreviewResponse

    @GET("$SERVICE_PREFIX?")
    suspend fun getBooksWithArgs(
        @Query("where") args: String
    ): List<BooksSeriesResponse>

    @GET("$SERVICE_PREFIX?")
    suspend fun getBookSeries(
        @Query("where") args: String,
        @Query("sortBy") sortOrder: String
    ): List<BooksSeriesResponse>

    suspend fun getBooksInfo()
}