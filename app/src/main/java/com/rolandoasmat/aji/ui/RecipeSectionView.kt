package com.rolandoasmat.aji.ui

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.rolandoasmat.aji.R
import kotlinx.android.synthetic.main.view_recipe_section.view.*

class RecipeSectionView(context: Context, section: RecipeSectionViewUiModel, callbacks: RecipesRecyclerView.Callbacks): ConstraintLayout(context) {

    init {
        inflate(context, R.layout.view_recipe_section, this)
        recipesListView?.setCallback(callbacks)
        when (section) {
            is RecipeSectionViewUiModel.VerticalColumn -> {
                sectionLabel?.text = section.title
                recipesListView?.setData(section.items,
                    SectionType.VERTICAL_COLUMN
                )
            }
            is RecipeSectionViewUiModel.HorizontalRow -> {
                sectionLabel?.text = section.title
                recipesListView?.setData(section.items,
                    SectionType.HORIZONTAL_ROW
                )
            }
            is RecipeSectionViewUiModel.Grid -> {
                sectionLabel?.text = section.title
                recipesListView?.setData(section.items,
                    SectionType.GRID
                )
            }
        }
    }

    enum class SectionType {
        VERTICAL_COLUMN,
        HORIZONTAL_ROW,
        GRID
    }


}