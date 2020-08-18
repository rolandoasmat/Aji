package com.rolandoasmat.aji.repositories

import GetRecipeDetailsQuery
import ListRecipesQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.apollographql.apollo.ApolloQueryCall
import com.rolandoasmat.aji.network.ApiSuccessResponse
import com.rolandoasmat.aji.network.NetworkBoundResource
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.db.DatabaseRepository
import com.rolandoasmat.aji.db.FavoriteRecipeEntity
import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.network.AjiApolloClient
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val databaseRepository: DatabaseRepository,
    private val ajiApolloClient: AjiApolloClient) {

    fun fetchRecipes(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, ListRecipesQuery.Data>(coroutineContextProvider) {
            override fun createCall(): ApolloQueryCall<ListRecipesQuery.Data> {
                val query = ListRecipesQuery()
                return ajiApolloClient.query(query)
            }

            override fun processResponse(response: ApiSuccessResponse<ListRecipesQuery.Data>): List<Recipe> {
                return response.body.listRecipes()?.items()?.map { item ->
                    Recipe(item.id(), item.title(), item.thumbnailURL(), item.sectionTitle())
                } ?: emptyList()
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
                val recipeDetails = response.body.recipeDetails ?: throw IllegalStateException("Empty recipe details")
                return RecipeDetails(
                    recipeDetails.id(),
                    recipeDetails.title() ?: "",
                    recipeDetails.imageURL(),
                    recipeDetails.description() ?: "",
                    recipeDetails.ingredients(),
                    recipeDetails.steps())
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