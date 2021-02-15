package com.rolandoasmat.aji.saved

import com.rolandoasmat.aji.db.FavoriteRecipeEntity
import com.rolandoasmat.aji.ui.RecipeSectionViewUiModel

data class SavedUiModel(val recipes: List<RecipeSectionViewUiModel.Entry>) {

    companion object {

        fun fromRecipes(recipes: List<FavoriteRecipeEntity>): SavedUiModel {
            val entries = recipes.map { entry ->
                RecipeSectionViewUiModel.Entry(entry.recipeID, entry.name, entry.posterURL)
            }
            return SavedUiModel(entries)
        }
    }
}