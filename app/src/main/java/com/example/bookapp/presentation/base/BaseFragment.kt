package com.example.bookapp.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId), FragmentNavigation {

    private var fragmentNavigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation)
            fragmentNavigation = context
    }

    override fun onDetach() {
        super.onDetach()
        fragmentNavigation = null
    }

    override fun pushFragment(kClass: KClass<out Fragment>, args: Bundle) {
        fragmentNavigation?.pushFragment(kClass, args)
    }

    override fun replaceFragment(kClass: KClass<out Fragment>, args: Bundle) {
        fragmentNavigation?.replaceFragment(kClass, args)
    }

    override fun popFragment(popDepth: Int) {
        fragmentNavigation?.popFragment(popDepth)
    }

}