package com.rolandoasmat.aji.ui

import com.rolandoasmat.aji.model.Recipe

data class RecipesGridViewUiModel(val sections: List<RecipeSectionViewUiModel>) {

    enum class Sort {
        BY_CATEGORIES,
        BY_TITLE
    }

    companion object {

        fun from(recipes: List<Recipe>, sort: Sort = Sort.BY_TITLE ): RecipesGridViewUiModel {
            val sections = when(sort) {
                Sort.BY_CATEGORIES -> sortByCategory(recipes)
                Sort.BY_TITLE -> sortByTitle(recipes)
            }
            return RecipesGridViewUiModel(sections)
        }

        /**
         * Sort the recipes by category in the following order:
         * - Breakfast
         * - Appetizers
         * - Entrees
         * - Dessert
         * - Drinks
         */
        private fun sortByCategory(recipes: List<Recipe>): List<RecipeSectionViewUiModel> {
            val sections = mutableListOf<RecipeSectionViewUiModel>()
            val items = recipes.map {
                RecipeSectionViewUiModel.Entry.from(it)
            }
            val section = RecipeSectionViewUiModel.Grid("Items", items)
            sections.add(section)
            return sections
        }

        private fun sortByTitle(recipes: List<Recipe>): List<RecipeSectionViewUiModel> {
            val entries = recipes
                .sortedBy {
                    it.title
                }.map {
                    RecipeSectionViewUiModel.Entry.from(it)
                }
            val section = RecipeSectionViewUiModel.Grid("Recipes", entries)
            return listOf(section)
        }
    }
}