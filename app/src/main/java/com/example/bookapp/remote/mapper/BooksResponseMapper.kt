package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BooksResponse
import javax.inject.Inject

class BooksResponseMapper @Inject constructor() : Mapper<BooksResponse, List<BookItemModel>> {
    override fun invoke(response: BooksResponse): List<BookItemModel> {
        return emptyList()
    }
}