package com.rolandoasmat.aji.recipe_details

data class RecipeDetailsUIModel(
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>)