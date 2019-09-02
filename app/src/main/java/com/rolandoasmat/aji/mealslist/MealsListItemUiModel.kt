package com.rolandoasmat.aji.mealslist

/**
 * Represents an item of the meals list.
 *
 * @property backdropURL URL of backdrop image to use
 * @property mealTitle Name of the meal
 */
data class MealsListItemUiModel(val backdropURL: String,
                                val mealTitle: String)