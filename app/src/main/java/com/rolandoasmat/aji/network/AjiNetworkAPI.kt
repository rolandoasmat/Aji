package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import retrofit2.Response

interface AjiNetworkAPI {

    fun fetchBreakfastPlates(): Response<List<Recipe>>

    fun fetchDinnerPlates(): Response<List<Recipe>>

    fun fetchAppetizerPlates(): Response<List<Recipe>>

    fun fetchDessertPlates(): Response<List<Recipe>>

    fun fetchDrinks(): Response<List<Recipe>>

    fun fetchRecipeDetails(id: Int): Response<RecipeDetails>
}