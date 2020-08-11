package com.rolandoasmat.aji.model

data class RecipeDetails(
    val id: String,
    val title: String,
    val posterURL: String?,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>)