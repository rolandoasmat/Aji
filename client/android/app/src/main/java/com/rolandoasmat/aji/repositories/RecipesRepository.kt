package com.rolandoasmat.aji.repositories

import GetRecipeDetailsQuery
import ListRecipesQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.apollographql.apollo.ApolloQueryCall
import com.rolandoasmat.aji.network.ApiSuccessResponse
import com.rolandoasmat.aji.network.NetworkBoundResource
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.db.FavoriteRecipeEntity
import com.rolandoasmat.aji.model.*
import com.rolandoasmat.aji.network.AjiApolloClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val databaseRepository: DatabaseRepository,
    private val ajiApolloClient: AjiApolloClient) {

    fun fetchRecipes(): LiveData<Resource<Recipes>> {
        return object : NetworkBoundResource<Recipes, ListRecipesQuery.Data>(coroutineContextProvider) {
            override fun createCall(): ApolloQueryCall<ListRecipesQuery.Data> {
                val query = ListRecipesQuery()
                return ajiApolloClient.query(query)
            }

            override fun processResponse(response: ApiSuccessResponse<ListRecipesQuery.Data>): Recipes {
                return response.body.getRecipes!!.fragments.recipesFragment.toRecipes()
            }
        }.asLiveData()
    }

    fun fetchRecipeDetails(id: String): LiveData<Resource<RecipeDetails>> {
        return object: NetworkBoundResource<RecipeDetails, GetRecipeDetailsQuery.Data>(coroutineContextProvider) {
            override fun createCall(): ApolloQueryCall<GetRecipeDetailsQuery.Data> {
                val query = GetRecipeDetailsQuery(id)
                return ajiApolloClient.query(query)
            }

            override fun processResponse(response: ApiSuccessResponse<GetRecipeDetailsQuery.Data>): RecipeDetails {
                return response.body.getRecipeDetails!!.fragments.recipeDetailsFragment.toRecipeDetails()
            }
        }.asLiveData()
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