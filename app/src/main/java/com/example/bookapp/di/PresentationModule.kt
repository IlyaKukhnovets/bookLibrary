package com.example.bookapp.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module

@Module(
    includes = [
        BooksModule::class
    ]
)

interface PresentationModule {
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}