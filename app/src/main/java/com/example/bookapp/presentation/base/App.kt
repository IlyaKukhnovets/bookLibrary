package com.example.bookapp.presentation.base

class App : BaseApplication() {
    companion object {
        var INSTANCE: BaseApplication? = null
            private set
    }


    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
    }
}