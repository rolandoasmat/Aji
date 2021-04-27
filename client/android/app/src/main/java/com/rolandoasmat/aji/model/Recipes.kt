package com.rolandoasmat.aji.model

data class Recipes(
    val items: List<RecipeGroup>,
    val nextToken: String?
)