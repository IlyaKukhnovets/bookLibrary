package com.example.bookapp.data.model.book

data class BooksInfoModel(
    val count: Int,
    val author: String?,
    val genre: String?
)

class CombinedBooksInfo(
    val genreItems: List<BooksInfoModel>,
    val authorItems: List<BooksInfoModel>
)
