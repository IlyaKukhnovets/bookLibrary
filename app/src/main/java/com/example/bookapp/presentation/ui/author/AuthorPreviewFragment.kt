package com.example.bookapp.presentation.ui.author

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.data.state.LoadingResult
import com.example.bookapp.databinding.FragmentAuthorPreviewBinding
import com.example.bookapp.di.Injectable
import com.example.bookapp.presentation.base.BaseFragment
import com.example.bookapp.presentation.extensions.gone
import com.example.bookapp.presentation.extensions.injectViewModel
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.viewstate.AuthorItemViewState
import javax.inject.Inject

class AuthorPreviewFragment : BaseFragment(R.layout.fragment_author_preview), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentAuthorPreviewBinding::bind)
    private val viewModel by lazy { injectViewModel<AuthorPreviewViewModel>(factory) }
    private val authorId by lazy { arguments?.getInt("KEY_AUTHOR_ID") ?: -1 }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initViewModel() {
        viewModel.loadAuthor(authorId)
        viewModel.authorLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingResult.Loading -> {
                    binding.lProgress.llProgress.show()
                    binding.lProgress.tvErrorMessage.gone()
                }
                is LoadingResult.Success -> {
                    binding.lProgress.llProgress.gone()
                    initLayout(it.data)
                }
                is LoadingResult.Error -> {
                    binding.lProgress.llProgress.gone()
                    binding.lProgress.tvErrorMessage.show()
                }
            }
        }
    }

    private fun initLayout(it: List<AuthorItemViewState>) {
        it.map {
            Glide.with(binding.root)
                .load(it.image)
                .transform(RoundedCorners(8))
                .into(binding.ivAuthorImage)

            binding.tvAuthorName.text = it.name
            binding.tvBiography.text = it.biography
        }
    }

}