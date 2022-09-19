package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksContainerBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.base.ViewPager2Adapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.AuthorItemViewState
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class BooksFragmentContainer : BaseFragment(R.layout.fragment_books_container), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentBooksContainerBinding::bind)
    //    private val viewModel by lazy { injectViewModel<BooksContainerViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(mapper = ::mapItem)
    private lateinit var viewModel: BooksContainerViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        val fragments = listOf(
            MyBooksFragment::class to Bundle.EMPTY,
            MyFavouriteBooksFragment::class to Bundle.EMPTY
        )
        val titles = listOf(
            "Книги", "Избранное"
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
        viewModel = injectViewModel(factory)
        viewModel.authorsLiveData.observe(viewLifecycleOwner) {
            adapter.replaceElementsWithDiffUtil(it)
        }
    }

    private fun mapItem(viewState: AuthorItemViewState) = BookTopItem(viewState)

}