package com.example.bookapp.di.domain

import com.example.bookapp.data.repository.AuthorsRepository
import com.example.bookapp.data.repository.AuthorsRepositoryImpl
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.data.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindsBooksRepository(repository: BooksRepositoryImpl): BooksRepository

    @Binds
    fun bindsAuthorsRepository(repository: AuthorsRepositoryImpl): AuthorsRepository

}