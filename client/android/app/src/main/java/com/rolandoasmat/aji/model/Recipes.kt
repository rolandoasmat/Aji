package com.rolandoasmat.aji.model

import fragment.RecipesFragment

data class Recipes(
    val items: List<RecipeGroup>,
    val nextToken: String?
)

fun RecipesFragment.toRecipes(): Recipes {
    val items = items.map {
        it.fragments.recipeGroupFragment.toRecipeGroup()
    }
    return Recipes(items, nextToken)
}