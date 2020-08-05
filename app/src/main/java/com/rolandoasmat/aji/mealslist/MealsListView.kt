package com.rolandoasmat.aji.mealslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom view that encapsulates a list
 * of meals
 */
class MealsListView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs), MealListItemCallbacks {

    private var callbacks: MealListItemCallbacks? = null
    private val mealsAdapter: MealsListAdapter = MealsListAdapter(this)

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = mealsAdapter
    }

    /**
     * Set the list of meals data
     */
    fun setData(data: MealsListUiModel) {
        mealsAdapter.setData(data.meals)
    }

    fun setCallback(callbacks: MealListItemCallbacks) {
        this.callbacks = callbacks
    }

    // Callbacks

    override fun onImageTapped(index: Int) {
        callbacks?.onImageTapped(index)
    }
}