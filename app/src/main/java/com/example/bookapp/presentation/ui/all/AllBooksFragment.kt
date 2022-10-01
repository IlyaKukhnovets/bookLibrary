package com.example.bookapp.presentation.ui.all

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentAllBooksBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BasePaginationAdapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.ui.home.BookItem
import com.example.bookapp.presentation.viewstate.home.BookItemViewState
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.math.abs

class AllBooksFragment : BaseFragment(R.layout.fragment_all_books), Injectable {

    companion object {
        const val KEY_AUTHOR = "KEY_AUTHOR"
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentAllBooksBinding::bind)
    private val viewModel by lazy { injectViewModel<AllBooksViewModel>(factory) }
    private val adapter = BasePaginationAdapter(
        mapper = ::mapItems
    )
    private val author by lazy {
        arguments?.getString(KEY_AUTHOR) ?: ""
    }

    private fun mapItems(viewState: BookItemViewState) = BookItem(viewState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.rvList.adapter = adapter
        binding.swipeLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun initViewModel() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.tvAuthorHeader.text = "Все книги автора\n\"$author\" "
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset + appBarLayout.totalScrollRange) == 0) {
                binding.collapsingToolbar.title = author
            } else {
                binding.collapsingToolbar.title = null
            }
        }
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.Header_Bold_Primary)
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.Header_Bold_Primary)
        viewModel.init(author)
        lifecycleScope.launchWhenStarted {
            viewModel.getFlow().collectLatest {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.NotLoading -> binding.swipeLayout.isRefreshing = false
                    is LoadState.Loading -> binding.swipeLayout.isRefreshing = true
                    is LoadState.Error -> binding.swipeLayout.isRefreshing = false
                }
            }
        }
    }
}