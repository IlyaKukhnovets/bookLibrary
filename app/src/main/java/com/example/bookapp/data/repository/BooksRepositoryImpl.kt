package com.example.bookapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookapp.data.datasource.BooksDataSource
import com.example.bookapp.data.model.book.BookItemModel
import com.example.bookapp.data.model.book.BookPreviewModel
import com.example.bookapp.data.model.book.BooksSeriesModel
import com.example.bookapp.data.model.book.CombinedBooksInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

    override fun getBooksListWithArgs(argument: String): PagingSource<Int, BookItemModel> =
        object : PagingSource<Int, BookItemModel>() {
            override fun getRefreshKey(state: PagingState<Int, BookItemModel>): Int? = null

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItemModel> {
                return try {
                    val offset = params.key ?: 0
                    val limit = params.loadSize
                    val data = booksDataSource.getBooksListWithArgs(limit, offset, argument)
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

    override suspend fun getBookById(bookId: String): BookPreviewModel {
        return withContext(ioDispatcher) {
            booksDataSource.getBookById(bookId)
        }
    }

    override suspend fun getBookSeries(series: String): List<BooksSeriesModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getBookSeries(series)
        }
    }

    override suspend fun getAuthorBooks(authorName: String): List<BooksSeriesModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getAuthorBooks(authorName)
        }
    }

    override suspend fun getRelativeBooks(genre: String, bookId: Int): List<BooksSeriesModel> {
        return withContext(ioDispatcher) {
            booksDataSource.getRelativeBooks(genre, bookId)
        }
    }

    override suspend fun getBooksInfo(): Flow<CombinedBooksInfo> {
        return withContext(ioDispatcher) {
            val genreItems = booksDataSource.getBooksInfo("genre")
            val authorItems = booksDataSource.getBooksInfo("author")
            genreItems.combine(authorItems) { genre, author ->
                CombinedBooksInfo(
                    genreItems = genre,
                    authorItems = author
                )
            }
        }
    }
}