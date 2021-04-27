package com.rolandoasmat.aji.model

data class RecipeDetails(
    val id: String,
    val imageURL: String,
    val info: RecipeInfo,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val title: String
)