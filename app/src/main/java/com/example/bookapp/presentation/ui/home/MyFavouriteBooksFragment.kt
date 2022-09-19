package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentMyFavouriteBooksBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.BookItemViewState
import javax.inject.Inject

class MyFavouriteBooksFragment : BaseFragment(R.layout.fragment_my_favourite_books), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentMyFavouriteBooksBinding::bind)
    private val viewModel by lazy { injectViewModel<MyFavouriteBooksViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(mapper = ::mapItems)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.rvList.rvRecycler.adapter = adapter
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshBooks()
        }
    }

    private fun initViewModel() {
        viewModel.favouriteBooksLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {}
                is LoadingResult.Success -> {
                    binding.refreshLayout.isRefreshing = false
                    adapter.replaceElementsWithDiffUtil(it.data)
                }
                is LoadingResult.Error -> {
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun mapItems(viewState: BookItemViewState) = BookItem(viewState)
}