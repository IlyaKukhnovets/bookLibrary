package com.example.bookapp.presentation.extensions

import android.view.View
import android.view.View.*

fun View.show(show: Boolean, invisible: Boolean = false) {
    visibility = if (show) VISIBLE else if (invisible) INVISIBLE else GONE
}

fun View.gone() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}