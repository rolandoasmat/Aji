package com.rolandoasmat.aji.model.mappers

import ListRecipesQuery
import com.rolandoasmat.aji.model.Recipe

object RecipeMapper {

    fun recipeFromGQLRecipe(gqlRecipe: ListRecipesQuery.Item ): Recipe {
        val category = CategoryMapper.categoryFromString(gqlRecipe.sectionTitle())
        return Recipe(gqlRecipe.id(), gqlRecipe.title(), gqlRecipe.thumbnailURL()!!, category)
    }
}