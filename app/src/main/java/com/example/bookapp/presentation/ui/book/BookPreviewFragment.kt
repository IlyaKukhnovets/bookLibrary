package com.example.bookapp.presentation.ui.book

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentBookPreviewBinding
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.extensions.injectViewModel
import javax.inject.Inject

class BookPreviewFragment @Inject constructor(private val factory: ViewModelProvider.Factory) :
    BaseFragment() {

    private val viewModel by lazy { injectViewModel<BookPreviewViewModel>(factory) }
    private val binding by viewBinding(FragmentBookPreviewBinding::bind)

    companion object {
        private const val KEY_BOOK_ID = "BookPreviewFragment.KEY_BOOK_ID"

        fun getArgs(bookId: Int) = bundleOf(
            KEY_BOOK_ID to bookId
        )
    }

    private val bookId by lazy { arguments?.getInt(KEY_BOOK_ID) ?: -1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {

    }

    private fun initViewModel() {
        viewModel.loadBookById(bookId)
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {}
                is LoadingResult.Success -> {}
                is LoadingResult.Error -> {}
            }
        }
    }

}