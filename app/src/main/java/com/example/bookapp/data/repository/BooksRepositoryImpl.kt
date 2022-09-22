package com.example.bookapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookapp.data.datasource.BooksDataSource
import com.example.bookapp.data.model.BookItemModel
import com.example.bookapp.data.model.BookPreviewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksDataSource: BooksDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BooksRepository {

    override fun getBooksList(status: Int?): PagingSource<Int, BookItemModel> =
        object : PagingSource<Int, BookItemModel>() {
            override fun getRefreshKey(state: PagingState<Int, BookItemModel>): Int? {
                return null
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItemModel> {
                return try {
                    val offset = params.key ?: 0
                    val limit = params.loadSize
                    val data = booksDataSource.getBooksList(limit, offset, status)
                    return LoadResult.Page(
                        data = data,
                        prevKey = null,
                        nextKey = if (data.size == limit) offset + data.size else null
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

        }

    override suspend fun getBookById(bookId: Int): List<BookPreviewModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getBookById(bookId)
        }
    }
}