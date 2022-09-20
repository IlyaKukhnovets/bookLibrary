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
    Fragment(contentLayoutId), HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (this is Injectable) {
            AndroidSupportInjection.inject(this)
        }
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }

}