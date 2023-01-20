package com.example.bookapp.presentation.ui.info

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBooksBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.viewstate.info.BookInfoViewState
import javax.inject.Inject

class BooksInfoFragment : BaseFragment(R.layout.fragment_books), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentBooksBinding::bind)
    private val viewModel by lazy { injectViewModel<BooksInfoViewModel>(factory) }
    private val adapter = BaseRecyclerViewAdapter(
        mapper = ::mapItems
    )

    private fun mapItems(vs: BookInfoViewState) = BooksInfoItem(vs)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.rvRecycler.adapter = adapter
        initViewModel()
    }

    private fun initViewModel() {

    }

}