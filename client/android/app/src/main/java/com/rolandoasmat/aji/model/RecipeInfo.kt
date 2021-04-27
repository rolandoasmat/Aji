package com.rolandoasmat.aji.model

import fragment.RecipeInfoFragment

data class RecipeInfo(
    val description: String,
    val duration: String,
    val servings: String
)

fun RecipeInfoFragment.toRecipeInfo(): RecipeInfo {
    return RecipeInfo(description, duration, servings)
}