package com.example.bookapp.di.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bookapp.remote.moshi.MoshiProvider
import com.example.bookapp.remote.service.AuthorsService
import com.example.bookapp.remote.service.BooksService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RemoteModule {

    private const val NETWORK_TIMEOUT = 15L
    private const val REMOTE_PATH = "https://affinedhall.backendless.app/api/data"

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("$REMOTE_PATH/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return MoshiProvider.getInstance()
    }

    @Provides
    fun providesChuck(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor
            .Builder(context)
            .build()
    }

    @Provides
    fun providesApiOkHttpClient(chuckInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
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

    @Provides
    fun providesAuthorsService(retrofit: Retrofit): AuthorsService {
        return retrofit.create(AuthorsService::class.java)
    }
}