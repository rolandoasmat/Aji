package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface AjiNetworkService {

    fun getHomeScreen(): Flow<Resource<HomeScreenData>>

    fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>>
}