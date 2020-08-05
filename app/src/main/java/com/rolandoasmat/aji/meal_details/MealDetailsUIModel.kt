package com.rolandoasmat.aji.meal_details

data class MealDetailsUIModel(
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>)