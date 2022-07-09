package com.example.bookapp.di.remote

import com.example.bookapp.remote.moshi.MoshiProvider
import com.example.bookapp.remote.service.BooksService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RemoteModule {

    private const val NETWORK_TIMEOUT = 15L
    private const val REMOTE_PATH = "https://jiggishattack.backendless.app/api/data"

    @Provides
    fun providesRetrofit(converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$REMOTE_PATH/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return MoshiProvider.getInstance()
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