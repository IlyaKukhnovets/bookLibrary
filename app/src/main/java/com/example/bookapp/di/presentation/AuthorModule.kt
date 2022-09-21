package com.example.bookapp.di.presentation

import androidx.lifecycle.ViewModel
import com.example.bookapp.di.keys.ViewModelKey
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

    @Binds
    @IntoMap
    @ViewModelKey(AuthorPreviewViewModel::class)
    fun bindAuthorPreviewViewModel(viewModel: AuthorPreviewViewModel): ViewModel
}