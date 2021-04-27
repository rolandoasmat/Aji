package com.rolandoasmat.aji.model

import fragment.RecipeFragment

data class Recipe(
    val id: String,
    val thumbnailURL: String?,
    val title: String
)


fun RecipeFragment.toRecipe(): Recipe {
    return Recipe(id, thumbnailURL, title)
}