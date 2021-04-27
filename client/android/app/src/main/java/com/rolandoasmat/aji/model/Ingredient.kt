package com.rolandoasmat.aji.model

import fragment.IngredientFragment

data class Ingredient(
    val amount: String,
    val name: String
)

fun IngredientFragment.toIngredient(): Ingredient {
    return Ingredient(amount, name)
}