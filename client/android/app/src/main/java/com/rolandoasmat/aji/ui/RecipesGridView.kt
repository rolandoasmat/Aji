package com.rolandoasmat.aji.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.forEach

class RecipesGridView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), RecipesRecyclerView.Callbacks {

    private var callback: Callback? = null

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

    fun setCallback( callback: Callback) {
        this.callback = callback
    }

    override fun onImageTapped(recipeID: String) {
        callback?.onRecipeClick(recipeID)
    }

    interface Callback {
        fun onRecipeClick(recipeID: String)
    }

}