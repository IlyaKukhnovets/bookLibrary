package com.example.bookapp.di.presentation

import androidx.lifecycle.ViewModel
import com.example.bookapp.di.keys.ViewModelKey
import com.example.bookapp.presentation.ui.info.BooksInfoFragment
import com.example.bookapp.presentation.ui.info.BooksInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface InfoModule {

    @ContributesAndroidInjector
    fun bindBookInfoFragment(): BooksInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(BooksInfoViewModel::class)
    fun bindBooksInfoViewModel(viewModel: BooksInfoViewModel): ViewModel
}