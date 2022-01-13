package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.model.RecipeGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AjiNetworkServiceProd(
    private val AjiApolloClient: AjiApolloClient
    ): AjiNetworkService
{
    override fun getHomeScreen(): Flow<Resource<HomeScreenData>> {
        val breakfastRecipes = listOf(Recipe(id = "1231", thumbnailURL = null, title = "Pan de Chicharron"))
        val recipeGroups = listOf(RecipeGroup(name = "Breakfast", recipes = breakfastRecipes))
        val data = HomeScreenData(recipeGroups = recipeGroups)
        return flow { emit(Resource.success(data)) }
    }

    override fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>> {
        return flow { }
    }
}