package com.example.bookapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksBinding
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.BookItemTopViewState
import javax.inject.Inject

class MyBooksFragment @Inject constructor(private val factory: ViewModelProvider.Factory) :
    BaseFragment(R.layout.fragment_books) {

    companion object {
        fun getArguments() = bundleOf()
    }

    private val binding by viewBinding(FragmentBooksBinding::bind)

    private val viewModel by lazy { injectViewModel<MyBooksViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(mapper = ::mapViewState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.list.rvRecycler.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            adapter.replaceElementsWithDiffUtil(it)
        }
    }

    private fun mapViewState(viewState: BookItemTopViewState) = BookItem(viewState)
}