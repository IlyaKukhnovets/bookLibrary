package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentBooksContainerBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.base.ViewPager2Adapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.ui.author.AuthorPreviewViewModel
import com.example.bookapp.presentation.ui.base.KEY_ARGS
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewState
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class BooksFragmentContainer : BaseFragment(R.layout.fragment_books_container), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentBooksContainerBinding::bind)
    private val viewModel by lazy { injectViewModel<BooksContainerViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(
        mapper = ::mapItem,
        onItemClickListener = ::itemListener
    )

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
        viewModel.authorsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {}
                is LoadingResult.Success -> {
                    adapter.replaceElementsWithDiffUtil(it.data)
                }
                is LoadingResult.Error -> {}
            }
        }
    }

    private fun mapItem(viewState: AuthorItemViewState) = BookTopItem(viewState)

    private fun itemListener(item: AuthorItemViewState) {
        val bundle = bundleOf(
            KEY_ARGS to AuthorPreviewViewModel.AuthorPreviewArgs(
                item.objectId,
                item.genre,
                item.id,
                item.name
            )
        )
        findNavController().navigate(
            R.id.action_booksFragmentContainer_to_authorPreviewFragment,
            bundle
        )
    }

}