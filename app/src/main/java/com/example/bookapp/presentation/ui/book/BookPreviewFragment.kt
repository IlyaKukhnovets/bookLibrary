package com.example.bookapp.presentation.ui.book

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentBookPreviewBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.BookPreviewViewState
import javax.inject.Inject

class BookPreviewFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { injectViewModel<BookPreviewViewModel>(factory) }
    private val binding by viewBinding(FragmentBookPreviewBinding::bind)
    private val bookId by lazy { arguments?.getInt("KEY_BOOK_ID") ?: -1 }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.loadBookById(bookId)
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {
                    binding.refreshLayout.isRefreshing = true
                }
                is LoadingResult.Success -> {
                    binding.refreshLayout.isRefreshing = false
                    initView(it.data)
                }
                is LoadingResult.Error -> {
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun initView(vs: BookPreviewViewState) {
        Glide.with(binding.root).load(vs.image).into(binding.ivBackgroundTop)
        Glide.with(binding.root).load(vs.image).into(binding.ivBookImage)
        binding.tvBookName.text = vs.bookName
        binding.tvBookAuthor.text = vs.author
        binding.tvBookDescription.text = vs.bookDescription
        binding.tvBookPagesCount.text = vs.pagesCount.toString()
    }

}