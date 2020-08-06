package com.rolandoasmat.aji.recipe_details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecipeDetailsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val ingredientsFragment = IngredientsFragment()
    private val fragments = listOf(ingredientsFragment, StepsFragment())
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = fragments[position]

    fun setIngredients(ingredients: List<String>) {
        ingredientsFragment.setData(ingredients)
    }
}