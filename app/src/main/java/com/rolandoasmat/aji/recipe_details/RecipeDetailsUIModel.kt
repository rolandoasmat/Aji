package com.rolandoasmat.aji.recipe_details

import com.rolandoasmat.aji.ingredients.IngredientUIModel
import com.rolandoasmat.aji.steps.StepUIModel

data class RecipeDetailsUIModel(
    val recipeID: String,
    val title: String,
    val posterURL: String?,
    val description: String,
    val cookingTime: String,
    val servingSize: String,
    val ingredients: List<IngredientUIModel>,
    val steps: List<StepUIModel>
)