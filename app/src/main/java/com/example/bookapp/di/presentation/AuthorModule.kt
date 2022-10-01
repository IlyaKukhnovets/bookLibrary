package com.example.bookapp.di.presentation

import androidx.lifecycle.ViewModel
import com.example.bookapp.di.keys.ViewModelKey
import com.example.bookapp.presentation.ui.all.AllBooksFragment
import com.example.bookapp.presentation.ui.all.AllBooksViewModel
import com.example.bookapp.presentation.ui.author.AuthorPreviewFragment
import com.example.bookapp.presentation.ui.author.AuthorPreviewViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface AuthorModule {

    @ContributesAndroidInjector
    fun bindAuthorPreviewFragment(): AuthorPreviewFragment

    @ContributesAndroidInjector
    fun bindAllAuthorBooksFragment(): AllBooksFragment

    @Binds
    @IntoMap
    @ViewModelKey(AuthorPreviewViewModel::class)
    fun bindAuthorPreviewViewModel(viewModel: AuthorPreviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllBooksViewModel::class)
    fun bindAllBooksViewModel(viewModel: AllBooksViewModel): ViewModel
}