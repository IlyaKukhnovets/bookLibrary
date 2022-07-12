package com.example.bookapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBookTopBinding
import com.example.bookapp.databinding.ItemMyBooksBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.BookItemTopViewState

class BookItem(override val viewState: BookItemTopViewState) :
    BaseRecyclerItem<ItemMyBooksBinding, BookItemTopViewState> {

    override fun getViewId(): Int = R.layout.item_my_books

    override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemMyBooksBinding {
        return ItemMyBooksBinding::inflate
    }

    override fun renderView(binding: ItemMyBooksBinding, positionInAdapter: Int) {
        Glide.with(binding.root)
            .load(viewState.image)
            .transform(RoundedCorners(8))
            .into(binding.ivBookImage)
        binding.tvBookName.text = viewState.name
        binding.tvBookAuthor.text = viewState.author
    }

}