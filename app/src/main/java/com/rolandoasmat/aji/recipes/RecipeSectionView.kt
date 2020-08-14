package com.rolandoasmat.aji.recipes

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.recipes_recyclerview.RecipesRecyclerView
import kotlinx.android.synthetic.main.view_recipe_section.view.*

class RecipeSectionView(context: Context, section: RecipesUIModel.Section, callbacks: RecipesRecyclerView.Callbacks): ConstraintLayout(context) {

    init {
        inflate(context, R.layout.view_recipe_section, this)
        recipesListView?.setCallback(callbacks)
        when (section) {
            is RecipesUIModel.Section.SingleCard -> {
                sectionLabel?.text = section.title
                // TODO create single card layout option
            }
            is RecipesUIModel.Section.HorizontalRow -> {
                sectionLabel?.text = section.title
                recipesListView?.setData(section.items, SectionType.HORIZONTAL_ROW)
            }
            is RecipesUIModel.Section.Grid -> {
                sectionLabel?.text = section.title
                recipesListView?.setData(section.items, SectionType.GRID)
            }
        }
    }

    enum class SectionType {
        HORIZONTAL_ROW,
        GRID
    }


}