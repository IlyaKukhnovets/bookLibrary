package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentMyFavouriteBooksBinding
import com.example.bookapp.presentation.base.BaseFragment
import javax.inject.Inject

class MyFavouriteBooksFragment @Inject constructor() :
    BaseFragment(R.layout.fragment_my_favourite_books) {

    private val binding by viewBinding(FragmentMyFavouriteBooksBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
        }
    }
}