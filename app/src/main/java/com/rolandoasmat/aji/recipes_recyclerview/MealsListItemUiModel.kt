package com.rolandoasmat.aji.recipes_recyclerview

/**
 * Represents an item of the meals list.
 *
 * @property backdropURL URL of backdrop image to use
 * @property mealTitle Name of the meal
 */
data class MealsListItemUiModel(
    val recipeID: String,
    val backdropURL: String?,
    val mealTitle: String)