package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksContainerBinding
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.base.ViewPager2Adapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.BookItemTopViewState
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class MainBooksFragmentContainer @Inject constructor(private val factory: ViewModelProvider.Factory) : BaseFragment(R.layout.fragment_books_container) {

    private val binding by viewBinding(FragmentBooksContainerBinding::bind)
    private val viewModel by lazy { injectViewModel<MyBooksTopViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(mapper = ::mapItem)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
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

        binding.rvTopBooks.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvTopBooks.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            adapter.replaceElementsWithDiffUtil(it)
        }
    }

    private fun mapItem(viewState: BookItemTopViewState) = BookTopItem(viewState)

}