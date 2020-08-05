package com.rolandoasmat.aji.recipe_details

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.model.RecipeDetails

class RecipeDetailsViewModel(recipesRepository: RecipesRepository): ViewModel() {

    private val _fetchDetails = MutableLiveData<Int>()
    private val fetchDetails = Transformations.switchMap(_fetchDetails) {
        recipesRepository.fetchRecipeDetails(it)
    }

    private val _details = MediatorLiveData<RecipeDetailsUIModel>().apply {
        addSource(fetchDetails) {
            handleRecipeDetailsResponse(it)
        }
    }

    val details: LiveData<RecipeDetailsUIModel>
        get() = _details

    fun getRecipeDetails(recipeID: Int) {
        _fetchDetails.value = recipeID
    }

    private fun handleRecipeDetailsResponse(response: Resource<RecipeDetails>) {

    }

}