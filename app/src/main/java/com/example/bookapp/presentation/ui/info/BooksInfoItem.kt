package com.example.bookapp.presentation.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBooksInfoBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.info.BookInfoViewState

class BooksInfoItem(
    override val viewState: BookInfoViewState
) : BaseRecyclerItem<ItemBooksInfoBinding, BookInfoViewState> {

    override fun getViewId(): Int = R.layout.item_books_info

    override fun getBindingInflater():
                (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) ->
    ItemBooksInfoBinding {
        return ItemBooksInfoBinding::inflate
    }

    override fun renderView(binding: ItemBooksInfoBinding, positionInAdapter: Int) {
        with(binding) {
            tvTitle.text = viewState.genre
            tvCount.text = viewState.count.toString()
        }
    }
}