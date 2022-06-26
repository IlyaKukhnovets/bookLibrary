package com.example.bookapp.presentation.viewstate

interface Mapper<M, VS> {
    operator fun invoke(entity: M): VS
}