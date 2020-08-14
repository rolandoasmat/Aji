package com.rolandoasmat.aji.recipes_recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.recipes.RecipeSectionView
import com.rolandoasmat.aji.recipes.RecipesUIModel

/**
 * Custom view that encapsulates a list
 * of meals
 */
class RecipesRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private val mealsAdapter = MealsListAdapter()

    init {
        adapter = mealsAdapter
        makeHorizontal()
    }

    //region Public

    /**
     * Set the list of meals data
     */
    fun setData(data: List<RecipesUIModel.Entry>, sectionType: RecipeSectionView.SectionType) {
        updateSectionType(sectionType)
        mealsAdapter.setData(data, sectionType)
    }

    fun setCallback(callbacks: Callbacks) {
        mealsAdapter.setCallbacks(callbacks)
    }
    //endregion

    private fun updateSectionType(type: RecipeSectionView.SectionType) {
        when (type) {
            RecipeSectionView.SectionType.HORIZONTAL_ROW -> makeHorizontal()
            RecipeSectionView.SectionType.GRID -> makeGrid()
        }
    }

    private fun makeHorizontal() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun makeGrid() {
        layoutManager = GridLayoutManager(context, 2)
    }

    interface Callbacks {
        fun onImageTapped(recipeID: String)
    }
}