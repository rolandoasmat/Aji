package com.rolandoasmat.aji.network

import com.apollographql.apollo.ApolloQueryCall
import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.model.Recipes
import com.rolandoasmat.aji.model.toRecipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AjiNetworkServiceProd @Inject constructor(
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