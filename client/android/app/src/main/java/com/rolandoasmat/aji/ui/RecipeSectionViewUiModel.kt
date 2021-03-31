package com.rolandoasmat.aji.ui

import com.rolandoasmat.aji.model.Recipe

sealed class RecipeSectionViewUiModel {
    class VerticalColumn(val title: String, val items: List<Entry>): RecipeSectionViewUiModel()
    class HorizontalRow(val title: String, val items: List<Entry>): RecipeSectionViewUiModel()
    class Grid(val title: String, val items: List<Entry>): RecipeSectionViewUiModel()

    data class Entry(val recipeID: String, val title: String, val thumbnailURL: String?) {
        companion object {
            fun from(recipe: Recipe): Entry {
                return Entry(recipe.id, recipe.title, recipe.thumbnailURL)
            }
        }
    }
}

