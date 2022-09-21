package com.example.bookapp.presentation.ui.book

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentBookPreviewBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.viewstate.BookPreviewViewState
import com.example.bookapp.presentation.viewstate.BookStatus
import jp.wasabeef.glide.transformations.BlurTransformation
import javax.inject.Inject

class BookPreviewFragment : BaseFragment(R.layout.fragment_book_preview), Injectable {

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
                    binding.lProgress.root.show()
                    binding.lProgress.tvErrorMessage.gone()
                }
                is LoadingResult.Success -> {
                    binding.lProgress.root.gone()
                    initView(it.data)
                }
                is LoadingResult.Error -> {
                    binding.lProgress.root.gone()
                    binding.lProgress.tvErrorMessage.show()
                }
            }
        }
    }

    private fun initView(it: List<BookPreviewViewState>) {
        val builder = StringBuilder()
        it.map { viewState ->
            Glide.with(binding.root)
                .load(viewState.image)
                .transform(BlurTransformation(24))
                .into(binding.ivBackgroundTop)
            Glide.with(binding.root)
                .load(viewState.image)
                .transform(RoundedCorners(12))
                .into(binding.ivBookImage)

            binding.tvBookName.text = viewState.bookName
            binding.bookAuthorTitle.text = "Автор:"
            binding.tvBookAuthor.text = viewState.author
            binding.tvBookStatus.text = mapBookStatus(viewState.status)
            binding.tvBookDescriptionTitle.text = "О книге"
            binding.tvBookDescription.text = viewState.bookDescription
            binding.tvBookPagesCount.text =
                builder
                    .append(viewState.pagesCount.toString())
                    .append(" печатных стр.")

            binding.tvBookStatus.visibility = View.VISIBLE
        }
    }

    private fun mapBookStatus(status: BookStatus): String {
        return when (status) {
            BookStatus.READ -> "Хочу прочитать"
            BookStatus.FINISHED -> "Прочитал"
            BookStatus.FAVOURITE -> "Избранное"
            BookStatus.NOT_READ -> "Не читал"
        }
    }

}