package com.rolandoasmat.aji.mealslist

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom view that encapsulates a list
 * of meals
 */
class MealsListView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs), MealListItemCallbacks {


    private val mealsAdapter: MealsListAdapter = MealsListAdapter(this)

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = mealsAdapter
    }

    /**
     * Set the list of meals data
     */
    fun setData(data: MealsListUiModel) {
        mealsAdapter.setData(data.meals)

    }

    // Callbacks

    override fun onImageTapped(index: Int) {
        // TODO navigate to meal details destination
//        val action = FavoritesFragmentDirections.actionNavigationHomeToMealDetailsNavigation()
//        findNavController().navigate(action)

    }
}