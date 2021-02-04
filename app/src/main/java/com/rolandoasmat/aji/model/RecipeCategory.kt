package com.rolandoasmat.aji.model

data class RecipeCategory(
    val id: Int,
    val title: String,
    val type: RecipeCategoryType,
    val recipes: List<Recipe>)