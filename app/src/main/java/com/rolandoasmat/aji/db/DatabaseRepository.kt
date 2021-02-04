package com.rolandoasmat.aji.db

import com.rolandoasmat.aji.repositories.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DatabaseRepository constructor(
    private val dao: RecipesDAO,
    private val coroutineContextProvider: CoroutineContextProvider) {

    private val scope = CoroutineScope(coroutineContextProvider.io)


    fun saveFavoriteRecipe(recipe: FavoriteRecipeEntity) {
        scope.launch {
            dao.insertFavoriteRecipe(recipe)
        }
    }

    fun deleteFavoriteRecipe(recipeID: String) {
        scope.launch {
            dao.deleteFavoriteRecipe(recipeID)
        }
    }

    fun loadFavoriteRecipes() = dao.loadFavoriteRecipes()

    fun getFavoriteRecipe(recipeID: String) = dao.getFavoriteRecipe(recipeID)

}