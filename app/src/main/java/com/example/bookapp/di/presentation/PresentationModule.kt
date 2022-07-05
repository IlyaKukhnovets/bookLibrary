package com.example.bookapp.di.presentation

import androidx.lifecycle.ViewModelProvider
import com.example.bookapp.di.FragmentFactory
import com.example.bookapp.di.MainActivityModule
import com.example.bookapp.di.ViewModelFactory
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