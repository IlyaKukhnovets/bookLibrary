package com.example.bookapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksContainerBinding
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator

class MainBooksFragmentContainer : BaseFragment(R.layout.fragment_books_container) {

    companion object {
        fun getArguments() = bundleOf()
    }

    private val binding by viewBinding(FragmentBooksContainerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val fragments = listOf(
            MyBooksFragment::class to Bundle(),
            MyFavouriteBooksFragment::class to Bundle()
        )
        val titles = listOf(
            "My Books", "My favourite books"
        )
        val pagerAdapter = ViewPager2Adapter(this, fragments)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
        binding.viewPager.offscreenPageLimit = pagerAdapter.itemCount
    }

}