package com.example.bookapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBookTopBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.AuthorItemViewState
import com.example.bookapp.presentation.viewstate.BookItemViewState

class BookTopItem(override val viewState: AuthorItemViewState) :
    BaseRecyclerItem<ItemBookTopBinding, AuthorItemViewState> {

    override fun getViewId(): Int = R.layout.item_book_top

    override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemBookTopBinding {
        return ItemBookTopBinding::inflate
    }

    override fun renderView(binding: ItemBookTopBinding, positionInAdapter: Int) {
        Glide.with(binding.root)
            .load(viewState.image)
            .into(binding.ivBookImage)
    }
}