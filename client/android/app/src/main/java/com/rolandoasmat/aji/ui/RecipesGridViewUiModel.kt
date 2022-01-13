package com.rolandoasmat.aji.ui

import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.model.Recipe

data class RecipesGridViewUiModel(val sections: List<RecipeSectionViewUiModel>) {

    enum class Sort {
        BY_CATEGORIES,
        BY_TITLE
    }

    companion object {

        fun from(homeScreenData: HomeScreenData, sort: Sort = Sort.BY_CATEGORIES ): RecipesGridViewUiModel {
            val sections = when(sort) {
                Sort.BY_CATEGORIES -> sortByCategory(homeScreenData)
                Sort.BY_TITLE -> sortByTitle(homeScreenData)
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
        private fun sortByCategory(homeScreenData: HomeScreenData): List<RecipeSectionViewUiModel> {
            return homeScreenData.recipeGroups.map { group ->
                val items = group.recipes.map { recipe ->
                    RecipeSectionViewUiModel.Entry.from(recipe)
                }
                RecipeSectionViewUiModel.Grid(group.name, items)
            }
        }

        private fun sortByTitle(homeScreenData: HomeScreenData): List<RecipeSectionViewUiModel> {
            val recipes = mutableListOf<Recipe>()
            homeScreenData.recipeGroups.forEach { group ->
                recipes.addAll(group.recipes)
            }

            val entries = recipes
                .sortedBy {
                    it.title
                }.map {
                    RecipeSectionViewUiModel.Entry.from(it)
                }
            val section = RecipeSectionViewUiModel.Grid("Recipes - Alphabetical", entries)
            return listOf(section)
        }
    }
}