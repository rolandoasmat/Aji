package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.network.responses.RecipesResponse

interface AjiNetworkAPI {

    fun fetchRecipes(): RecipesResponse
}