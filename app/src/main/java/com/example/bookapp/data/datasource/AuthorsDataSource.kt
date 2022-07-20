package com.example.bookapp.data.datasource

import com.example.bookapp.data.model.BookAuthorItemModel

interface AuthorsDataSource {
    suspend fun getBookAuthors(): List<BookAuthorItemModel>
}