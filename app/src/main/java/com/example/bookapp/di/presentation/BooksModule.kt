package com.example.bookapp.di.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.bookapp.di.keys.FragmentKey
import com.example.bookapp.di.keys.ViewModelKey
import com.example.bookapp.presentation.ui.book.BookPreviewFragment
import com.example.bookapp.presentation.ui.book.BookPreviewViewModel
import com.example.bookapp.presentation.ui.home.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BooksModule {

    @Binds
    @IntoMap
    @FragmentKey(BooksFragmentContainer::class)
    fun bindBooksContainerFragment(fragment: BooksFragmentContainer): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MyBooksFragment::class)
    fun bindBooksFragment(fragment: MyBooksFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MyFavouriteBooksFragment::class)
    fun bindFavouriteBooksFragment(fragment: MyFavouriteBooksFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BookPreviewFragment::class)
    fun bindBookPreviewFragment(fragment: BookPreviewFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MyBooksViewModel::class)
    fun bindBooksViewModel(viewModel: MyBooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BooksContainerViewModel::class)
    fun bindBooksTopViewModel(viewModel: BooksContainerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyFavouriteBooksViewModel::class)
    fun bindFavouriteBooksViewModel(viewModel: MyFavouriteBooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookPreviewViewModel::class)
    fun bindBookPreviewViewModel(viewModel: BookPreviewViewModel): ViewModel
}