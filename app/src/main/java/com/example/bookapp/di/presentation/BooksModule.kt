package com.example.bookapp.di.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.bookapp.di.keys.FragmentKey
import com.example.bookapp.di.keys.ViewModelKey
import com.example.bookapp.presentation.ui.home.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BooksModule {

    @Binds
    @IntoMap
    @FragmentKey(MyBooksFragment::class)
    fun bindBooksFragment(fragment: MyBooksFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MainBooksFragmentContainer::class)
    fun bindBooksFragment(fragment: MainBooksFragmentContainer): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MyFavouriteBooksFragment::class)
    fun bindBooksFragment(fragment: MyFavouriteBooksFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MyBooksViewModel::class)
    fun bindBooksViewModel(viewModel: MyBooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyBooksTopViewModel::class)
    fun bindBooksViewModel(viewModel: MyBooksTopViewModel): ViewModel

}