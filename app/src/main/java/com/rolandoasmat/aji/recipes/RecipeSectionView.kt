package com.rolandoasmat.aji.recipes

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.rolandoasmat.aji.R
import kotlinx.android.synthetic.main.view_recipe_section.view.*

class RecipeSectionView(context: Context, section: RecipesUIModel.Section): ConstraintLayout(context) {

    init {
        inflate(context, R.layout.view_recipe_section, this)

        when (section) {
            is RecipesUIModel.Section.SingleCard -> {
                sectionLabel?.text = section.title
            }
            is RecipesUIModel.Section.HorizontalRow -> {
                sectionLabel?.text = section.title
                recipesListView?.makeHorizontal()
                recipesListView?.setData(section.items)
            }
            is RecipesUIModel.Section.Grid -> {
                sectionLabel?.text = section.title
                recipesListView?.makeGrid()
                recipesListView?.setData(section.items)
            }
        }
    }

}