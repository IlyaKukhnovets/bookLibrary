package com.example.bookapp.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

class BasePaginationAdapter<VS : Any>(
    private val mapper: (VS) -> BaseRecyclerItem<out ViewBinding, VS>,
    private val onItemClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null,
    private val onItemLongClickListener: ((item: VS, view: View, position: Int) -> Unit)? = null
) : PagingDataAdapter<BaseRecyclerItem<out ViewBinding, VS>, BaseViewHolder<ViewBinding, VS>>(
    DiffUtilItemCallback()
) {

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, VS>, position: Int) {
        getItem(position)?.let { holder.renderItem(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, VS> {
        fun findItem(viewType: Int): BaseRecyclerItem<out ViewBinding, VS> {
            for (i in 0..itemCount) {
                val item = getItem(i)
                if (item?.getViewId() == viewType) {
                    return item
                }
            }
            throw IllegalArgumentException("View type not found: $viewType")
        }

        val item = findItem(viewType)
        return BaseViewHolder(
            binding = item.getBindingInflater()
                .invoke(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener = onItemClickListener,
            onItemLongClickListener = onItemLongClickListener
        )
    }

    override fun getItemViewType(position: Int) = getItem(position)?.getViewId() ?: -1

    override fun onViewRecycled(holder: BaseViewHolder<ViewBinding, VS>) = holder.onViewRecycled()

    suspend fun submitList(pagingData: PagingData<VS>) {
        submitData(pagingData.map(mapper::invoke))
    }

    private class DiffUtilItemCallback<VS : Any> :
        DiffUtil.ItemCallback<BaseRecyclerItem<*, VS>>() {

        override fun areItemsTheSame(
            oldItem: BaseRecyclerItem<*, VS>,
            newItem: BaseRecyclerItem<*, VS>
        ): Boolean {
            return newItem.areItemsTheSame(oldItem)
        }

        override fun areContentsTheSame(
            oldItem: BaseRecyclerItem<*, VS>,
            newItem: BaseRecyclerItem<*, VS>
        ): Boolean {
            return newItem.areContentsTheSame(oldItem)
        }
    }
}




