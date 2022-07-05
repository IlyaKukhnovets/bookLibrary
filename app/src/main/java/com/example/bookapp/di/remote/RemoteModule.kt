package com.example.bookapp.di.remote

import com.example.bookapp.remote.service.BooksService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RemoteModule {

    private const val NETWORK_TIMEOUT = 15L

    @Provides
    fun providesRetrofit(converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesMoshiConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun providesBooksService(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }
}