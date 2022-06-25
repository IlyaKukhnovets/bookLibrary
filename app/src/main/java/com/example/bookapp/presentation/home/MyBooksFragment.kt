package com.example.bookapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksBinding
import com.example.bookapp.presentation.base.BaseFragment

class MyBooksFragment : BaseFragment(R.layout.fragment_books) {

    companion object {
        fun getArguments() = bundleOf()
    }

    private val binding by viewBinding(FragmentBooksBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){

    }
}