package com.example.bookapp.presentation.ui.book

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentBookPreviewBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.base.BaseRecyclerViewAdapter
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.ui.base.KEY_ARGS
import com.example.bookapp.presentation.viewstate.author.AuthorPreviewArgs
import com.example.bookapp.presentation.viewstate.book.BookPreviewViewState
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState
import com.example.bookapp.presentation.viewstate.book.MyBooksArgs
import jp.wasabeef.glide.transformations.BlurTransformation
import javax.inject.Inject

class BookPreviewFragment : BaseFragment(R.layout.fragment_book_preview), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy { injectViewModel<BookPreviewViewModel>(factory) }
    private val binding by viewBinding(FragmentBookPreviewBinding::bind)
    private val booksSeriesAdapter = BaseRecyclerViewAdapter(mapper = ::mapItems)
    private val relativeBooksAdapter = BaseRecyclerViewAdapter(mapper = ::mapItems)

    private fun mapItems(viewState: BooksSeriesViewState.ViewState) =
        BooksSeriesItem(viewState, ::onItemListener)

    private val args by lazy {
        arguments?.getParcelable<MyBooksArgs>(KEY_ARGS)
            ?: throw IllegalArgumentException("Need arguments")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvSeriesList.adapter = booksSeriesAdapter
        binding.rvSeriesList.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvRelativeBooks.adapter = relativeBooksAdapter
        binding.rvRelativeBooks.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.loadBookById(args.objectId)
            viewModel.loadBookSeries(args.series)
            viewModel.loadRelativeBooks(args.genre, args.bookId)
            binding.refreshLayout.isRefreshing = false
        }
        binding.ivBookImage.setOnClickListener {
            val bundle = bundleOf(
                KEY_ARGS to viewModel.getSavedImage()
            )
            findNavController().navigate(R.id.action_bookPreviewFragment_to_imageDialog, bundle)
        }
        binding.nestedScroll.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            when {
                oldScrollY > 90 -> {
                    binding.appBar.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.background_page,
                            null
                        )
                    )
                    binding.toolbar.title = viewModel.getSavedBook()
                }
                oldScrollY == 0 && scrollY > 90 -> {
                    binding.appBar.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.background_page,
                            null
                        )
                    )
                    binding.toolbar.title = viewModel.getSavedBook()
                }
                else -> {
                    binding.appBar.setBackgroundColor(Color.TRANSPARENT)
                    binding.toolbar.title = ""
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.loadBookById(args.objectId)
        viewModel.loadBookSeries(args.series)
        viewModel.loadRelativeBooks(args.genre, args.bookId)
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {
                    binding.lProgress.root.show()
                    binding.lProgress.tvErrorMessage.gone()
                }
                is LoadingResult.Success -> {
                    binding.lProgress.root.gone()
                }
                is LoadingResult.Error -> {
                    binding.lProgress.root.gone()
                    binding.lProgress.tvErrorMessage.show()
                }
            }
        }
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            setScreenData(it)
        }
        viewModel.booksSeriesLiveData.observe(viewLifecycleOwner) {
            binding.tvSeriesName.show(it.isShowTitle)
            binding.tvSeriesTitle.show(it.isShowTitle)
            booksSeriesAdapter.replaceElementsWithDiffUtil(it.state)
        }
        viewModel.relativeBooksLiveData.observe(viewLifecycleOwner) {
            binding.tvRelativeBooks.show(it.isShowTitle)
            relativeBooksAdapter.replaceElementsWithDiffUtil(it.state)
        }
    }

    private fun setScreenData(viewState: BookPreviewViewState) {
        val builder = StringBuilder()

        Glide.with(binding.root).load(viewState.image).transform(BlurTransformation(24))
            .into(binding.ivBackgroundTop)
        Glide.with(binding.root).load(viewState.image).transform(RoundedCorners(12))
            .into(binding.ivBookImage)

        binding.tvBookName.text = viewState.bookName
        binding.tvBookAuthor.text = viewState.author
        binding.tvBookStatus.text = viewState.status
        binding.tvBookDescription.text = viewState.bookDescription
        binding.tvBookPagesCount.text =
            builder.append(viewState.pagesCount.toString()).append(" печатных стр.")
        binding.tvBookStatus.visibility = View.VISIBLE
        binding.tvSeriesName.text = viewState.series
        binding.tvBookAuthor.setOnClickListener {
            viewState.authorObject?.let {
                val bundle = bundleOf(
                    KEY_ARGS to AuthorPreviewArgs(
                        it.objectId,
                        viewState.genre,
                        it.id,
                        viewState.author
                    )
                )
                findNavController().navigate(R.id.authorPreviewFragment, bundle)
            }
        }
        builder.clear()
    }

    private fun onItemListener(viewState: MyBooksArgs) {
        val bundle = bundleOf(
            KEY_ARGS to viewState
        )
        findNavController().navigate(R.id.bookPreviewFragment, bundle)
    }

}