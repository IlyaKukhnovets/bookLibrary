package com.example.bookapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.bookapp.R
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.presentation.ui.home.BooksFragmentContainer
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

//    @Inject
//    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
//        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        initView()
    }

    private fun initView() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun androidInjector(): DispatchingAndroidInjector<Any>? {
        return androidInjector
    }
}