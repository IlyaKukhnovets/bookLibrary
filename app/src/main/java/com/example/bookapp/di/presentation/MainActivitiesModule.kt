package com.example.bookapp.di

import com.example.bookapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @Module
    interface ViewModelModule {}

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    fun bindMainActivity(): MainActivity

}