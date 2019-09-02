package com.rolandoasmat.aji.mealslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom view that encapsulates a list
 * of meals
 */
class MealsListView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private val mealsAdapter: MealsListAdapter = MealsListAdapter()

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
}