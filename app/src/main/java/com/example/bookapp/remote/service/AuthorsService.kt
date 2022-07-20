package com.example.bookapp.remote.service

import com.example.bookapp.remote.model.AuthorsResponse
import retrofit2.http.GET

interface AuthorsService {

    companion object {
        private const val SERVICE_PREFIX = "Authors"
    }

    @GET(SERVICE_PREFIX)
    suspend fun getBookAuthors(): List<AuthorsResponse>
}