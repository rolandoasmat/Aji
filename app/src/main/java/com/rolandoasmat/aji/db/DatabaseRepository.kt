package com.rolandoasmat.aji.db

import com.rolandoasmat.aji.CoroutineContextProvider
import com.rolandoasmat.aji.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DatabaseRepository constructor(
    private val dao: RecipesDAO,
    private val coroutineContextProvider: CoroutineContextProvider) {

    private val scope = CoroutineScope(coroutineContextProvider.io)


    fun saveFavoriteRecipe(data: Recipe) {
        val recipe = FavoriteRecipeEntity(data.id, data.title, data.thumbnailURL)
        scope.launch {
            dao.insertFavoriteRecipe(recipe)
        }
    }

    fun deleteFavoriteRecipe(recipeID: Int) {
        scope.launch {
            dao.deleteFavoriteRecipe(recipeID)
        }
    }

    fun loadFavoriteRecipes() = dao.loadFavoriteRecipes()

    fun getFavoriteRecipe(recipeID: Int) = dao.getFavoriteRecipe(recipeID)

}