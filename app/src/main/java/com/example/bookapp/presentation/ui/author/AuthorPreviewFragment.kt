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
import com.example.bookapp.presentation.ui.all.AllBooksFragment
import com.example.bookapp.presentation.ui.base.KEY_ARGS
import com.example.bookapp.presentation.ui.book.BooksSeriesItem
import com.example.bookapp.presentation.viewstate.author.AuthorPreviewArgs
import com.example.bookapp.presentation.viewstate.author.AuthorRelativeViewState
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState
import com.example.bookapp.presentation.viewstate.book.MyBooksArgs
import com.example.bookapp.presentation.viewstate.home.AuthorItemViewState
import javax.inject.Inject

class AuthorPreviewFragment : BaseFragment(R.layout.fragment_author_preview), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentAuthorPreviewBinding::bind)
    private val viewModel by lazy { injectViewModel<AuthorPreviewViewModel>(factory) }

    private val args by lazy {
        arguments?.getParcelable<AuthorPreviewArgs>(KEY_ARGS)
            ?: throw IllegalArgumentException(
                "Need arguments for ${this.javaClass}"
            )
    }

    private val authorsAdapter = BaseRecyclerViewAdapter(
        mapper = ::mapAuthorItems,
        onItemClickListener = ::onAuthorClick
    )

    private val authorBooksAdapter = BaseRecyclerViewAdapter(
        mapper = ::mapBookItems
    )

    private fun mapAuthorItems(viewState: AuthorRelativeViewState.ViewState) =
        AuthorRelativeItem(viewState)

    private fun mapBookItems(viewState: BooksSeriesViewState.ViewState) =
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
            viewModel.loadScreenData(args.objectId)
            viewModel.loadRelativeAuthors(args.genre, args.authorId)
            viewModel.loadAuthorBooks(args.author)
            binding.refreshLayout.isRefreshing = false
        }
        binding.ivAllAuthorsBooks.setOnClickListener {
            val bundle = bundleOf(
                AllBooksFragment.KEY_AUTHOR to args.author
            )
            findNavController().navigate(
                R.id.action_authorPreviewFragment_to_allBooksFragment,
                bundle
            )
        }
    }

    private fun initViewModel() {
        viewModel.loadScreenData(args.objectId)
        viewModel.loadRelativeAuthors(args.genre, args.authorId)
        viewModel.loadAuthorBooks(args.author)
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
        viewModel.authorLiveData.observe(viewLifecycleOwner, ::observeLayout)
        viewModel.authorBooksLiveData.observe(viewLifecycleOwner, ::observeBooks)
        viewModel.relativeAuthorsLiveData.observe(viewLifecycleOwner) {
            binding.flRelativeAuthors.show(it.isShowTitle)
            authorsAdapter.replaceElementsWithDiffUtil(it.state)
        }
    }

    private fun observeBooks(it: BooksSeriesViewState) {
        val builder = StringBuilder()
        binding.flAuthorBooks.show(it.isShowTitle)
        binding.ivAllAuthorsBooks.show(it.isShowArrowButton)
        binding.tvBooksCount.text = builder
            .append(it.authorBooksCount)
            .append(" книги")
        authorBooksAdapter.replaceElementsWithDiffUtil(it.state)
        builder.clear()
    }

    private fun observeLayout(it: AuthorItemViewState) {
        Glide.with(binding.root)
            .load(it.image)
            .transform(CircleCrop())
            .into(binding.ivAuthorImage)

        binding.collapsingToolbar.title = it.name
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.Header_Bold)
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.Header_Bold_Primary)
        binding.tvBiography.text = it.biography
    }

    private fun onAuthorClick(item: AuthorRelativeViewState.ViewState) {
        val bundle = bundleOf(
            KEY_ARGS to AuthorPreviewArgs(
                item.objectId,
                item.genre,
                item.id,
                item.name
            )
        )
        findNavController().navigate(R.id.authorPreviewFragment, bundle)
    }

    private fun onBookClick(viewState: MyBooksArgs) {
        val bundle = bundleOf(
            KEY_ARGS to viewState
        )
        findNavController().navigate(R.id.bookPreviewFragment, bundle)
    }

}