package com.rolandoasmat.aji.recipes

data class RecipesUIModel(val sections: List<Section>) {

    sealed class Section {
        class VerticalColumn(val title: String, val items: List<Entry>): Section()
        class HorizontalRow(val title: String, val items: List<Entry>): Section()
        class Grid(val title: String, val items: List<Entry>): Section()
    }

    data class Entry(val recipeID: String, val title: String, val thumbnailURL: String?)
}