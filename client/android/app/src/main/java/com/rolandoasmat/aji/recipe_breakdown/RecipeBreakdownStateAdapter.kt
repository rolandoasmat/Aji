package com.rolandoasmat.aji.recipe_breakdown

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rolandoasmat.aji.model.RecipeBreakdownStep

class RecipeBreakdownStateAdapter(
    private val items: List<RecipeBreakdownStep>,
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount() = items.count()

    override fun createFragment(position: Int): Fragment {
        val item = items.get(position)
        return RecipeBreakdownItemFragment(item)
    }
}