package com.rolandoasmat.aji.home

import com.rolandoasmat.aji.model.Recipe

data class RecipesUIModel(val sections: List<Section>) {

    enum class Sort {
        BY_CATEGORIES,
        BY_TITLE
    }

    sealed class Section {
        class VerticalColumn(val title: String, val items: List<Entry>): Section()
        class HorizontalRow(val title: String, val items: List<Entry>): Section()
        class Grid(val title: String, val items: List<Entry>): Section()
    }

    data class Entry(val recipeID: String, val title: String, val thumbnailURL: String?) {

        companion object {
            fun from(recipe: Recipe): Entry {
                return Entry(recipe.id, recipe.title, recipe.thumbnailURL)
            }
        }
    }

    companion object {

        fun from(recipes: List<Recipe>, sort: Sort = Sort.BY_TITLE ): RecipesUIModel {
            val sections = when(sort) {
                Sort.BY_CATEGORIES -> sortByCategory(recipes)
                Sort.BY_TITLE -> sortByTitle(recipes)
            }
            return RecipesUIModel(sections)
        }

        /**
         * Sort the recipes by category in the following order:
         * - Breakfast
         * - Appetizers
         * - Entrees
         * - Dessert
         * - Drinks
         */
        private fun sortByCategory(recipes: List<Recipe>): List<Section> {
            val sections = mutableListOf<Section>()
            val items = recipes.map {
                Entry.from(it)
            }
            val section = Section.Grid("Items", items)
            sections.add(section)
            return sections
        }

        private fun sortByTitle(recipes: List<Recipe>): List<Section> {
            val entries = recipes
                .sortedBy {
                    it.title
                }.map {
                    Entry.from(it)
                }
            val section = Section.Grid("Recipes", entries)
            return listOf(section)
        }
    }
}