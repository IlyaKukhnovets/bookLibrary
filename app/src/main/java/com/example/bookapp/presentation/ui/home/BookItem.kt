package com.example.bookapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBookTopBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.BookItemTopViewState

class BookItem(override val viewState: BookItemTopViewState) :
    BaseRecyclerItem<ItemBookTopBinding, BookItemTopViewState> {

    override fun getViewId(): Int = R.layout.item_book_top

    override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemBookTopBinding {
        return ItemBookTopBinding::inflate
    }

    override fun renderView(binding: ItemBookTopBinding, positionInAdapter: Int) {
        TODO("Not yet implemented")
    }

}