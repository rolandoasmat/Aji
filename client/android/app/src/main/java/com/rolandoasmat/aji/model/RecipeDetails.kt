package com.rolandoasmat.aji.model

import fragment.RecipeDetailsFragment as RecipeDetailsFragmentGraphQL

data class RecipeDetails(
    val id: String,
    val imageURL: String?,
    val info: RecipeInfo,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val title: String
)

fun RecipeDetailsFragmentGraphQL.toRecipeDetails(): RecipeDetails {
    val info = this.info.fragments.recipeInfoFragment.toRecipeInfo()
    val ingredients = this.ingredients.map {
        it.fragments.ingredientFragment.toIngredient()
    }
    val steps = this.steps.map {
        Step(it)
    }
    return RecipeDetails(id, imageURL, info, ingredients, steps, title)
}