package com.example.bookapp.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        BooksModule::class,
        MainActivityModule::class,
    ]
)

interface PresentationModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindFragmentFactory(factory: FragmentFactory): androidx.fragment.app.FragmentFactory
}