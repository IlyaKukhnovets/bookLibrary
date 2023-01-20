package com.example.bookapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.splash.BottomNavCallback
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, BottomNavCallback {

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.gone()
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNav.setupWithNavController(findNavController(R.id.fragmentContainerView))
    }

    override fun androidInjector(): DispatchingAndroidInjector<Any>? {
        return androidInjector
    }

    override fun showBottomNavigation() {
        binding.bottomNav.show()
    }
}