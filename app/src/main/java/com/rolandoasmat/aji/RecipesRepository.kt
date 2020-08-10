package com.rolandoasmat.aji

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rolandoasmat.aji.db.DatabaseRepository
import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.network.AjiNetworkAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepository @Inject constructor(
    private val api: AjiNetworkAPI,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val databaseRepository: DatabaseRepository) {

    fun getBreakfastPlates(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, List<Recipe>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Recipe>>) = response.body
            override fun createCall() = api.fetchBreakfastPlates()
        }.asLiveData()
    }

    fun fetchDinnerPlates(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, List<Recipe>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Recipe>>) = response.body
            override fun createCall() = api.fetchDinnerPlates()
        }.asLiveData()
    }

    fun fetchAppetizerPlates(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, List<Recipe>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Recipe>>) = response.body
            override fun createCall() = api.fetchAppetizerPlates()
        }.asLiveData()
    }

    fun fetchDessertPlates(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, List<Recipe>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Recipe>>) = response.body
            override fun createCall() = api.fetchDessertPlates()
        }.asLiveData()
    }

    fun fetchDrinks(): LiveData<Resource<List<Recipe>>> {
        return object : NetworkBoundResource<List<Recipe>, List<Recipe>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Recipe>>) = response.body
            override fun createCall() = api.fetchDrinks()
        }.asLiveData()
    }

    fun fetchRecipeDetails(id: Int): LiveData<Resource<RecipeDetails>> {
        return object: NetworkBoundResource<RecipeDetails, RecipeDetails>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<RecipeDetails>) = response.body
            override fun createCall() = api.fetchRecipeDetails(id)
        }.asLiveData()
    }

    fun isFavoriteRecipe(recipeID: Int): LiveData<Boolean> {
        return Transformations.map(databaseRepository.getFavoriteRecipe(recipeID)) { entity ->
            entity != null
        }
    }

    fun removeFavoriteRecipe(recipeID: Int) = databaseRepository.deleteFavoriteRecipe(recipeID)

    fun saveFavoriteRecipe(recipe: Recipe) = databaseRepository.saveFavoriteRecipe(recipe)

}