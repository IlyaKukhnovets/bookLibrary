package com.example.bookapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

interface BaseRecyclerItem<BINDING : ViewBinding, VS : Any> {

    val viewState: VS

    @LayoutRes
    fun getViewId(): Int

    fun getBindingInflater(): (layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean) -> BINDING

    fun renderView(binding: BINDING, positionInAdapter: Int = 0)

    fun recyclerView(binding: BINDING?)

    fun areItemsTheSame(oldItem: BaseRecyclerItem<*, VS>?): Boolean {
        return viewState == oldItem?.viewState
    }

    fun areContentsTheSame(oldItem: BaseRecyclerItem<*, VS>?): Boolean {
        return viewState == oldItem?.viewState
    }
}