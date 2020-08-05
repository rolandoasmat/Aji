package com.rolandoasmat.aji.meal_details

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.model.RecipeDetails

class MealDetailsViewModel(recipesRepository: RecipesRepository): ViewModel() {

    private val _fetchDetails = MutableLiveData<Int>()
    private val fetchDetails = Transformations.switchMap(_fetchDetails) {
        recipesRepository.fetchRecipeDetails(it)
    }

    private val _details = MediatorLiveData<MealDetailsUIModel>().apply {
        addSource(fetchDetails) {
            handleRecipeDetailsResponse(it)
        }
    }

    val details: LiveData<MealDetailsUIModel>
        get() = _details

    fun getRecipeDetails(recipeID: Int) {
        _fetchDetails.value = recipeID
    }

    private fun handleRecipeDetailsResponse(response: Resource<RecipeDetails>) {

    }

}