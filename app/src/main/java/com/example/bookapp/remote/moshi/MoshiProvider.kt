package com.example.bookapp.remote.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.*

object MoshiProvider {

    @Volatile
    private var INSTANCE: Moshi? = null

    fun getInstance(): Moshi {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildMoshi().also {
                INSTANCE = it
            }
        }
    }

    private fun buildMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }
}