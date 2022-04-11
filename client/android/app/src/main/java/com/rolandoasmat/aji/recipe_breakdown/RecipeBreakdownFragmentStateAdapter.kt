package com.rolandoasmat.aji.recipe_breakdown

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rolandoasmat.aji.model.RecipeBreakdownStep

class RecipeBreakdownFragmentStateAdapter(
    private val items: List<RecipeBreakdownStep> ,
    fragment: RecipeBreakdownFragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount() = items.count()

    override fun createFragment(position: Int): Fragment {
        val data = items.get(position)
        return RecipeBreakdownItemFragment(data)
    }
}