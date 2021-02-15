package com.rolandoasmat.aji.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipesRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private val recipesAdapter = RecipesAdapter()

    init {
        adapter = recipesAdapter
        makeHorizontal()
    }

    //region Public
    fun setData(data: List<RecipeSectionViewUiModel.Entry>, sectionType: RecipeSectionView.SectionType) {
        updateSectionType(sectionType)
        recipesAdapter.setData(data, sectionType)
    }

    fun setCallback(callbacks: Callbacks) {
        recipesAdapter.setCallbacks(callbacks)
    }
    //endregion

    private fun updateSectionType(type: RecipeSectionView.SectionType) {
        when (type) {
            RecipeSectionView.SectionType.VERTICAL_COLUMN -> makeVertical()
            RecipeSectionView.SectionType.HORIZONTAL_ROW -> makeHorizontal()
            RecipeSectionView.SectionType.GRID -> makeGrid()
        }
    }

    private fun makeHorizontal() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun makeVertical() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun makeGrid() {
        layoutManager = GridLayoutManager(context, 2)
    }

    interface Callbacks {
        fun onImageTapped(recipeID: String)
    }
}