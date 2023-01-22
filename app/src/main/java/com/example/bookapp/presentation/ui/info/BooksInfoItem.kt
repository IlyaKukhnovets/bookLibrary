package com.example.bookapp.presentation.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.ItemAuthorsInfoBinding
import com.example.bookapp.databinding.ItemGenresInfoBinding
import com.example.bookapp.databinding.ItemPagesInfoBinding
import com.example.bookapp.databinding.ItemSectionHeaderBinding
import com.example.bookapp.presentation.base.BaseRecyclerItem
import com.example.bookapp.presentation.viewstate.info.BooksInfoBaseViewState

sealed class BooksInfoItem<B : ViewBinding>(override val viewState: BooksInfoBaseViewState) :
    BaseRecyclerItem<B, BooksInfoBaseViewState> {

    companion object {
        fun newInstance(viewState: BooksInfoBaseViewState): BooksInfoItem<*> {
            return when (viewState) {
                is BooksInfoBaseViewState.SectionHeader -> SectionItem(viewState)
                is BooksInfoBaseViewState.AuthorsViewState -> AuthorInfoItem(viewState)
                is BooksInfoBaseViewState.GenresViewState -> GenreInfoItem(viewState)
                is BooksInfoBaseViewState.PagesViewState -> PagesInfoItem(viewState)
            }
        }
    }

    class SectionItem(
        override val viewState: BooksInfoBaseViewState.SectionHeader
    ) : BooksInfoItem<ItemSectionHeaderBinding>(viewState) {

        override fun getViewId(): Int = R.layout.item_section_header

        override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemSectionHeaderBinding {
            return ItemSectionHeaderBinding::inflate
        }

        override fun renderView(binding: ItemSectionHeaderBinding, positionInAdapter: Int) {
            binding.root.text = viewState.title
        }
    }

    class AuthorInfoItem(override val viewState: BooksInfoBaseViewState.AuthorsViewState) :
        BooksInfoItem<ItemAuthorsInfoBinding>(viewState) {
        override fun getViewId(): Int = R.layout.item_authors_info

        override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemAuthorsInfoBinding {
            return ItemAuthorsInfoBinding::inflate
        }

        override fun renderView(binding: ItemAuthorsInfoBinding, positionInAdapter: Int) {
            with(binding) {
                tvTitle.text = viewState.author
                tvCount.text = viewState.count.toString()
            }
        }

    }

    class GenreInfoItem(
        override val viewState: BooksInfoBaseViewState.GenresViewState
    ) : BooksInfoItem<ItemGenresInfoBinding>(viewState) {

        override fun getViewId(): Int = R.layout.item_genres_info

        override fun getBindingInflater():
                    (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) ->
        ItemGenresInfoBinding {
            return ItemGenresInfoBinding::inflate
        }

        override fun renderView(binding: ItemGenresInfoBinding, positionInAdapter: Int) {
            with(binding) {
                tvTitle.text = viewState.genre
                tvCount.text = viewState.count.toString()
            }
        }
    }

    class PagesInfoItem(override val viewState: BooksInfoBaseViewState.PagesViewState) :
        BooksInfoItem<ItemPagesInfoBinding>(viewState) {
        override fun getViewId(): Int = R.layout.item_pages_info

        override fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> ItemPagesInfoBinding {
            return ItemPagesInfoBinding::inflate
        }

        override fun renderView(binding: ItemPagesInfoBinding, positionInAdapter: Int) {
            binding.tvTitle.text = viewState.title
        }
    }
}