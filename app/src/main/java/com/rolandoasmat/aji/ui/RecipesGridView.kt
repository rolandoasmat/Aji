package com.rolandoasmat.aji.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.forEach

class RecipesGridView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), RecipesRecyclerView.Callbacks {


    init {
        orientation = VERTICAL
    }

    fun setUiModel(uiModel: RecipesGridViewUiModel) {
        this.removeAllViews()
        uiModel.sections.forEach { section ->
            val sectionView = RecipeSectionView(context, section, this)
            addView(sectionView)

        }
    }

    override fun onImageTapped(recipeID: String) {
        // TODO
    }

}