package com.rolandoasmat.aji.repositories

import GetRecipeDetailsQuery
import ListRecipesQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.apollographql.apollo.ApolloQueryCall
import com.rolandoasmat.aji.db.FavoriteRecipeEntity
import com.rolandoasmat.aji.model.*
import com.rolandoasmat.aji.network.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val databaseRepository: DatabaseRepository,
    private val ajiNetworkService: AjiNetworkService) {

    fun fetchHomeScreenRecipes(): LiveData<Resource<HomeScreenData>> {
        return ajiNetworkService.getHomeScreen().asLiveData()
    }

    fun fetchRecipeDetails(id: String): LiveData<Resource<RecipeDetails>> {
        return ajiNetworkService.getRecipeDetails(id).asLiveData()
    }

    fun isFavoriteRecipe(recipeID: String): LiveData<Boolean> {
        return Transformations.map(databaseRepository.getFavoriteRecipe(recipeID)) { entity ->
            entity != null
        }
    }

    fun removeFavoriteRecipe(recipeID: String) = databaseRepository.deleteFavoriteRecipe(recipeID)

    fun saveFavoriteRecipe(recipeID: String, title: String, thumbnailURL: String?) = databaseRepository.saveFavoriteRecipe(
        FavoriteRecipeEntity(recipeID, title, thumbnailURL)
    )

    fun loadFavoriteRecipes() = databaseRepository.loadFavoriteRecipes()

}