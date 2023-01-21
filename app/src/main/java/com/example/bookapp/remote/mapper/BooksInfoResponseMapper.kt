package com.example.bookapp.remote.mapper

import com.example.bookapp.data.model.book.BooksInfoModel
import com.example.bookapp.remote.Mapper
import com.example.bookapp.remote.model.BooksInfoResponse
import javax.inject.Inject

class BooksInfoResponseMapper @Inject constructor() :
    Mapper<List<BooksInfoResponse>, List<BooksInfoModel>> {
    override fun invoke(response: List<BooksInfoResponse>): List<BooksInfoModel> {
        return response.map {
            BooksInfoModel(
                count = it.count,
                author = it.author,
                genre = it.genre
            )
        }
    }
}