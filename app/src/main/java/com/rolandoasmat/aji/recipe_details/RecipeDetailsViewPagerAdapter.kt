package com.rolandoasmat.aji.recipe_details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rolandoasmat.aji.info.InfoFragment
import com.rolandoasmat.aji.ingredients.IngredientsFragment
import com.rolandoasmat.aji.steps.StepsFragment

class RecipeDetailsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        InfoFragment(),
        IngredientsFragment(),
        StepsFragment()
    )
    override fun getItemCount() = fragments.count()
    override fun createFragment(position: Int) = fragments[position]
}