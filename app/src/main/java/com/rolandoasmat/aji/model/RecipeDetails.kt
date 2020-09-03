package com.rolandoasmat.aji.model

data class RecipeDetails(
    val id: String,
    val title: String,
    val posterURL: String?,
    val description: String,
    val cookingTime: String?,
    val servingSize: String?,
    val ingredients: List<Ingredient>,
    val steps: List<String>)