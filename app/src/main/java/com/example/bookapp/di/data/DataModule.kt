package com.example.bookapp.di.data

import com.example.bookapp.data.datasource.AuthorsDataSource
import com.example.bookapp.data.datasource.AuthorsDataSourceImpl
import com.example.bookapp.data.datasource.BooksDataSource
import com.example.bookapp.data.datasource.BooksDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [DataModule.ProvidesModule::class])
interface DataModule {

    @Binds
    fun bindsBooksDataSource(dataSource: BooksDataSourceImpl): BooksDataSource

    @Binds
    fun bindsAuthorsDataSource(dataSource: AuthorsDataSourceImpl): AuthorsDataSource

    @Module
    object ProvidesModule {
        @Provides
        fun providesIODispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}
