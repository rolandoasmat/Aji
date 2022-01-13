package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.model.RecipeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


class AjiNetworkServiceProd(
    private val AjiApolloClient: AjiApolloClient
    ): AjiNetworkService
{
    override fun getHomeScreen(): Flow<Resource<HomeScreenData>> {
        return flow {  }
    }

    override fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>> {
        return flow { }
    }
}