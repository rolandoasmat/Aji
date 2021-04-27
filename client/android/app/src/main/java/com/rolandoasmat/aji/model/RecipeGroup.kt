package com.rolandoasmat.aji.model

import fragment.RecipeGroupFragment

data class RecipeGroup(
    val name: String,
    val recipes: List<Recipe>
)

fun RecipeGroupFragment.toRecipeGroup(): RecipeGroup {
    val recipes = recipes.map {
        it.fragments.recipeFragment.toRecipe()
    }
    return RecipeGroup(name, recipes)
}