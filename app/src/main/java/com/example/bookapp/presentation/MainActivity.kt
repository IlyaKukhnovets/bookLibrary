package com.example.bookapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.R
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.presentation.ui.home.MainBooksFragmentContainer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc_fragment_container, MainBooksFragmentContainer()).commit()
    }

    private fun initViewModel() {

    }
}