package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.BooksResponse
import retrofit2.http.GET

interface BooksService {

    @GET
    suspend fun getBooks(): BooksResponse
}