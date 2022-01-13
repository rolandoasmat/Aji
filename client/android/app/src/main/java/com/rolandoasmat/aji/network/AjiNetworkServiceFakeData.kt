package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AjiNetworkServiceFakeData: AjiNetworkService {
    override fun getHomeScreen(): Flow<Resource<HomeScreenData>> {
        val breakfastRecipes = listOf(
            Recipe(id = "1231", thumbnailURL = null, title = "Pan de Chicharron"))
        val dinnerRecipes = listOf(
            Recipe(id = "39289", thumbnailURL = null, title = "Lomo Saltado"),
            Recipe(id = "6837", thumbnailURL = null, title = "Tallarines Verdes"),
            Recipe(id = "686567", thumbnailURL = null, title = "Aji de Gallina"))
        val recipeGroups = listOf(
            RecipeGroup(name = "Breakfast", recipes = breakfastRecipes),
            RecipeGroup(name = "Dinnder", recipes = dinnerRecipes))
        val data = HomeScreenData(recipeGroups = recipeGroups)
        return flow { emit(Resource.success(data)) }
    }

    override fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>> {
        val info = RecipeInfo(
            description = "A delicious beed stir fry served with rice and fries.",
            duration = "45 min",
            servings = "5 servings"
        )
        val ingredients = listOf(
            Ingredient(amount = "1 lb", name = "beef tenderloin")
        )
        val steps = listOf(
            Step(description = "cut everything"),
            Step(description = "make the rice"),
            Step(description = "cook the beef"))
        val data = RecipeDetails(id = id,
            imageURL = null,
            info = info,
            ingredients = ingredients,
            steps = steps,
            title = "Lomo Saltado"
        )
        return flow { emit(Resource.success(data))}
    }
}