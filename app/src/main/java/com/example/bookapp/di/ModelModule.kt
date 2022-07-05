package com.example.bookapp.di

import com.example.bookapp.di.data.DataModule
import com.example.bookapp.di.domain.DomainModule
import com.example.bookapp.di.local.LocalModule
import com.example.bookapp.di.remote.RemoteModule
import dagger.Module

@Module(
    includes = [
        DataModule::class,
        DomainModule::class,
        LocalModule::class,
        RemoteModule::class
    ]
)

interface ModelModule