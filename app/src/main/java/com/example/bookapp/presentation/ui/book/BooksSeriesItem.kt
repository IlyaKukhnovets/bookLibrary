package com.example.bookapp.presentation.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBooksSeriesBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState

class BooksSeriesItem(
    override val viewState: BooksSeriesViewState.ViewState,
    private val itemListener: (String, String) -> Unit
) : BaseRecyclerItem<ItemBooksSeriesBinding, BooksSeriesViewState.ViewState> {

    override fun getViewId() = R.layout.item_books_series

    override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemBooksSeriesBinding {
        return ItemBooksSeriesBinding::inflate
    }

    override fun renderView(binding: ItemBooksSeriesBinding, positionInAdapter: Int) {
        Glide.with(binding.root)
            .load(viewState.img)
            .transform(RoundedCorners(8))
            .into(binding.ivBookSeries)
        binding.tvBookName.text = viewState.bookName
        binding.tvBookAuthor.text = viewState.author

        binding.ivBookSeries.setOnClickListener {
            itemListener.invoke(viewState.objectId, viewState.series)
        }
    }
}