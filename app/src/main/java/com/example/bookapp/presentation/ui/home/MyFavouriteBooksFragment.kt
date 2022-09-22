package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentMyFavouriteBooksBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BasePaginationAdapter
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.viewstate.BookItemViewState
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MyFavouriteBooksFragment : BaseFragment(R.layout.fragment_my_favourite_books), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentMyFavouriteBooksBinding::bind)
    private val viewModel by lazy { injectViewModel<MyFavouriteBooksViewModel>(factory) }
    private val adapter = BasePaginationAdapter(
        mapper = ::mapItems,
        onItemClickListener = ::itemListener
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.rvList.rvRecycler.adapter = adapter
        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun initViewModel() {
        viewModel.init()
        lifecycleScope.launchWhenResumed {
            viewModel.getFlow().collectLatest {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    LoadState.Loading -> {
                        binding.refreshLayout.isRefreshing = true
                        binding.rvList.llProgress.tvErrorMessage.gone()
                    }
                    is LoadState.NotLoading -> {
                        binding.refreshLayout.isRefreshing = false
                    }
                    is LoadState.Error -> {
                        binding.rvList.llProgress.tvErrorMessage.show()
                        binding.refreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }

    private fun mapItems(viewState: BookItemViewState) = BookItem(viewState)

    private fun itemListener(item: BookItemViewState, view: View, position: Int) {
        val bundle = bundleOf(
            "KEY_BOOK_ID" to item.id
        )
        findNavController().navigate(
            R.id.action_booksFragmentContainer_to_bookPreviewFragment,
            bundle
        )
    }
}