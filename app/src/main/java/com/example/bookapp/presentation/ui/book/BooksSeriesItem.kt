package com.example.bookapp.presentation.ui.book

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemBooksSeriesBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.extensions.show
import com.example.bookapp.presentation.viewstate.book.BooksSeriesViewState
import com.example.bookapp.presentation.viewstate.book.MyBooksArgs

class BooksSeriesItem(
    override val viewState: BooksSeriesViewState.ViewState,
    private val itemListener: (MyBooksArgs) -> Unit
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
        binding.tvBookSeriesOrder.show(viewState.isShowOrder && viewState.order != 0)
        binding.tvBookSeriesOrder.text = viewState.order.toString()
        if (!viewState.isShowOrder) binding.tvBookName.gravity = Gravity.CENTER
        else Gravity.START and Gravity.CENTER_VERTICAL

        binding.ivBookSeries.setOnClickListener {
            itemListener.invoke(
                MyBooksArgs(
                    objectId = viewState.objectId,
                    series = viewState.series,
                    genre = viewState.genre,
                    bookId = viewState.id
                )
            )
        }
    }
}