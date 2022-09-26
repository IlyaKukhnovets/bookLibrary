package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentMyFavouriteBooksBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BasePaginationAdapter
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.ui.base.KEY_ARGS
import com.example.bookapp.presentation.ui.book.BookPreviewViewModel
import com.example.bookapp.presentation.viewstate.home.BookItemViewState
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.math.abs

class MyFavouriteBooksFragment : BaseFragment(R.layout.fragment_my_favourite_books), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentMyFavouriteBooksBinding::bind)
    private val viewModel by lazy { injectViewModel<MyFavouriteBooksViewModel>(factory) }
    private val adapter = BasePaginationAdapter(
        mapper = ::mapItems,
        onItemClickListener = ::itemListener
    )

    private val gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return when {
                    distanceX > 10 && abs(distanceY) < abs(distanceX) -> true
                    distanceX < -10 && abs(distanceY) < abs(distanceX) -> true
                    else -> false
                }
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.rvList.rvRecycler.adapter = adapter
        binding.rvList.rvRecycler.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(e)
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
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

    private fun itemListener(item: BookItemViewState) {
        val bundle = bundleOf(
            KEY_ARGS to BookPreviewViewModel.MyBooksArgs(
                item.objectId,
                item.series,
                item.genre
            )
        )
        findNavController().navigate(
            R.id.action_booksFragmentContainer_to_bookPreviewFragment,
            bundle
        )
    }
}