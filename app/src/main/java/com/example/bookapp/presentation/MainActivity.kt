package com.example.bookapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.bookapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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