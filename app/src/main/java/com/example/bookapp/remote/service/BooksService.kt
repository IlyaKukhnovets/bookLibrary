package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.BooksResponse
import retrofit2.http.GET

interface BooksService {

    companion object {
        private const val SERVICE_PREFIX = "Books"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBooks(): List<BooksResponse>
}