package com.example.bookapp.remote

interface Mapper<R, M> {
    operator fun invoke(response: R): M
}