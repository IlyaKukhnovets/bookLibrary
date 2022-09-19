package com.example.bookapp.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.bookapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId), FragmentNavigation, HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    private var fragmentNavigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (this is Injectable) {
            AndroidSupportInjection.inject(this)
        }
        if (context is FragmentNavigation)
            fragmentNavigation = context
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
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