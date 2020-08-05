package com.rolandoasmat.aji.recipe_details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecipeDetailsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragments = listOf(IngredientsFragment(), StepsFragment())
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = fragments[position]
}