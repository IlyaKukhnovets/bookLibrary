package com.example.bookapp.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KFunction0

class BaseRecyclerViewAdapter<VS : Any>(
    private val mapper: (VS) -> BaseRecyclerItem<out ViewBinding, VS>,
    private val onItemClickListener: ((item: VS) -> Unit)? = null,
    private val onItemLongClickListener: ((item: VS) -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder<ViewBinding, VS>>() {

    private val dataset = mutableListOf<BaseRecyclerItem<out ViewBinding, VS>>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, VS> {
        val item = dataset.find { it.getViewId() == viewType }
            ?: throw IllegalStateException("View type not found: $viewType")
        return BaseViewHolder(
            binding = item.getBindingInflater().invoke(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClickListener = onItemClickListener,
            onItemLongClickListener = onItemLongClickListener
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, VS>, position: Int) {
        holder.renderItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    override fun getItemViewType(position: Int): Int = dataset[position].getViewId()

    override fun onViewRecycled(holder: BaseViewHolder<ViewBinding, VS>) = holder.onViewRecycled()

    fun replaceElementsWithDiffUtil(items: List<VS>, enableAnimation: Boolean = true) {
        val diffUtilCallback = DiffUtilCallback(dataset.toList(), items.map(mapper))
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, enableAnimation)
        replaceElements(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun replaceElements(newElements: List<VS>) {
        dataset.clear()
        dataset.addAll(newElements.map(mapper))
    }

    private class DiffUtilCallback<VS : Any>(
        private var oldList: List<BaseRecyclerItem<*, VS>>,
        private var newList: List<BaseRecyclerItem<*, VS>>
    ) : DiffUtil.Callback() {

        /*
        * Returns the size of the old list.
        */
        override fun getOldListSize(): Int = oldList.size

        /*
        * Returns the size of the new list.
        */
        override fun getNewListSize(): Int = newList.size

        /*
        * Called by the DiffUtil to decide whether two object represent the same Item.
        */
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return newItem.areItemsTheSame(oldItem)
        }

        /*
        * Called by the DiffUtil when it wants to check whether two items have the same data.
        * DiffUtil uses this information to detect if the contents of an item has changed.
        */
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return newItem.areContentsTheSame(oldItem)
        }

    }

}