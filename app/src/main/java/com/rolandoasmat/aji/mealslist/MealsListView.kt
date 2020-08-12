package com.rolandoasmat.aji.mealslist

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
class MealsListView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs), MealListItemCallbacks {

    private var callbacks: MealListItemCallbacks? = null
    private val mealsAdapter = MealsListAdapter(this)

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

    fun setCallback(callbacks: MealListItemCallbacks) {
        this.callbacks = callbacks
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

    // Callbacks

    override fun onImageTapped(recipeID: String) {
        callbacks?.onImageTapped(recipeID)
    }
}