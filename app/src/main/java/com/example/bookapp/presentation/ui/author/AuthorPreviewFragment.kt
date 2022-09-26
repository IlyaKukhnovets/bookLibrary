package com.example.bookapp.presentation.ui.author

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentAuthorPreviewBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.ui.book.BooksSeriesItem
import com.example.bookapp.presentation.ui.home.BooksFragmentContainer
import com.example.bookapp.presentation.viewstate.author.AuthorRelativeViewState
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewState
import javax.inject.Inject

class AuthorPreviewFragment : BaseFragment(R.layout.fragment_author_preview), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentAuthorPreviewBinding::bind)
    private val viewModel by lazy { injectViewModel<AuthorPreviewViewModel>(factory) }

    private val objectId by lazy {
        arguments?.getString(BooksFragmentContainer.KEY_AUTHOR_OBJECT_ID) ?: ""
    }
    private val genre by lazy {
        arguments?.getString(BooksFragmentContainer.KEY_AUTHOR_GENRE) ?: ""
    }
    private val authorId by lazy {
        arguments?.getInt(BooksFragmentContainer.KEY_AUTHOR_ID) ?: -1
    }
    private val authorName by lazy {
        arguments?.getString(BooksFragmentContainer.KEY_AUTHOR) ?: ""
    }

    private val authorsAdapter = BaseRecyclerViewAdapter(
        mapper = ::mapAuthorItems,
        onItemClickListener = ::onAuthorClick
    )

    private val authorBooksAdapter = BaseRecyclerViewAdapter(
        mapper = ::mapBookItems
    )

    private fun mapAuthorItems(viewState: AuthorRelativeViewState) = AuthorRelativeItem(viewState)
    private fun mapBookItems(viewState: BooksSeriesViewState) =
        BooksSeriesItem(viewState, ::onBookClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvAuthorsList.adapter = authorsAdapter
        binding.rvAuthorsList.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvAuthorBooks.adapter = authorBooksAdapter
        binding.rvAuthorBooks.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.loadScreenData(objectId)
            viewModel.loadRelativeAuthors(genre, authorId)
            viewModel.loadAuthorBooks(authorName)
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun initViewModel() {
        viewModel.loadScreenData(objectId)
        viewModel.loadRelativeAuthors(genre, authorId)
        viewModel.loadAuthorBooks(authorName)
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {
                    binding.lProgress.llProgress.show()
                    binding.lProgress.tvErrorMessage.gone()
                }
                is LoadingResult.Success -> {
                    binding.lProgress.llProgress.gone()
                }
                is LoadingResult.Error -> {
                    binding.lProgress.llProgress.gone()
                    binding.lProgress.tvErrorMessage.show()
                }
            }
        }
        viewModel.authorLiveData.observe(viewLifecycleOwner) {
            initLayout(it)
        }
        viewModel.authorBooksLiveData.observe(viewLifecycleOwner) {
            binding.flAuthorBooks.show(viewModel.isShowAuthorsBookSection())
            authorBooksAdapter.replaceElementsWithDiffUtil(it)
        }
        viewModel.relativeAuthorsLiveData.observe(viewLifecycleOwner) {
            binding.tvRelativeAuthorsTitle.show(viewModel.isShowAuthorsHeader())
            authorsAdapter.replaceElementsWithDiffUtil(it)
        }
    }

    private fun initLayout(it: AuthorItemViewState) {
        Glide.with(binding.root)
            .load(it.image)
            .transform(CircleCrop())
            .into(binding.ivAuthorImage)

        binding.collapsingToolbar.title = it.name
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.Header_Bold)
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.Header_Bold_Primary)
        binding.tvBiography.text = it.biography
        binding.tvRelativeAuthorsTitle.text = "Похожие авторы"
    }

    private fun onAuthorClick(viewState: AuthorRelativeViewState, view: View, position: Int) {
        val bundle = bundleOf(
            BooksFragmentContainer.KEY_AUTHOR_OBJECT_ID to viewState.objectId,
            BooksFragmentContainer.KEY_AUTHOR_GENRE to viewState.genre,
            BooksFragmentContainer.KEY_AUTHOR_ID to viewState.id,
            BooksFragmentContainer.KEY_AUTHOR to viewState.name
        )
        findNavController().navigate(R.id.authorPreviewFragment, bundle)
    }

    private fun onBookClick(objectId: String, bookSeries: String) {
        val bundle = bundleOf(
            BooksFragmentContainer.KEY_BOOK_OBJECT_ID to objectId,
            BooksFragmentContainer.KEY_BOOK_SERIES to bookSeries
        )
        findNavController().navigate(R.id.bookPreviewFragment, bundle)
    }

}