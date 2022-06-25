package com.example.bookapp.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

interface FragmentNavigation {
    fun pushFragment(kClass: KClass<out Fragment>, args: Bundle = Bundle())
    fun replaceFragment(kClass: KClass<out Fragment>, args: Bundle = Bundle())
    fun popFragment(popDepth: Int = 1)
}