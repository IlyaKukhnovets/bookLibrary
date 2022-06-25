package com.example.bookapp.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<BINDING : ViewBinding, VS : Any>(
    private val binding: BINDING,
    private val onItemClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null,
    private val onItemLongClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    private var item: BaseRecyclerItem<BINDING, VS>? = null

    @Suppress("UNCHECKED_CAST")
    fun renderItem(holderItem: BaseRecyclerItem<out BINDING, VS>) {
        item = holderItem as BaseRecyclerItem<BINDING, VS>
        holderItem.let {
            it.renderView(binding, absoluteAdapterPosition)
            setClickListeners(itemView, it)
        }
    }

    private fun setClickListeners(view: View?, holderItem: BaseRecyclerItem<out BINDING, VS>) {
        onItemClickListener?.let {
            view?.setOnClickListener {
                it(holderItem.viewState, view, absoluteAdapterPosition)
            }
        }
        onItemLongClickListener?.let {
            view?.setOnLongClickListener {
                it(holderItem.viewState, view, absoluteAdapterPosition)
                true
            }
        }

        view?.setOnLongClickListener {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.invoke(holderItem.viewState, view, absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
            return@setOnLongClickListener false
        }

    }

    fun onViewRecycled() {
        item?.recyclerView(binding)
    }

}