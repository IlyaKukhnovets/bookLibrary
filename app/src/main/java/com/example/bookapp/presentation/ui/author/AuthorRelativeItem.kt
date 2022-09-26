package com.example.bookapp.presentation.ui.author

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemRelativeAuthorsBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.author.AuthorRelativeViewState

class AuthorRelativeItem(override val viewState: AuthorRelativeViewState.ViewState) :
    BaseRecyclerItem<ItemRelativeAuthorsBinding, AuthorRelativeViewState.ViewState> {

    override fun getViewId(): Int = R.layout.item_relative_authors

    override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemRelativeAuthorsBinding {
        return ItemRelativeAuthorsBinding::inflate
    }

    override fun renderView(binding: ItemRelativeAuthorsBinding, positionInAdapter: Int) {
        Glide.with(binding.root)
            .load(viewState.img)
            .transform(RoundedCorners(8))
            .into(binding.ivRelativeAuthor)
        binding.tvAuthorName.text = viewState.name
    }
}