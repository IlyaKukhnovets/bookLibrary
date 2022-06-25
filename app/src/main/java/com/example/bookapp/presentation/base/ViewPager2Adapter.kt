package com.example.bookapp.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.reflect.KClass

class ViewPager2Adapter(
    private val parentFragment: Fragment,
    private val fragments: List<Pair<KClass<out Fragment>, Bundle?>>
) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        parentFragment.context?.let {
            return parentFragment.childFragmentManager.fragmentFactory.instantiate(
                it.classLoader,
                fragments[position].first.java.name
            ).apply {
                arguments = fragments[position].second
            }
        }
        throw IllegalStateException("Context is null")
    }
}