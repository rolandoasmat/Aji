package com.rolandoasmat.aji.recipe_details

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails

class RecipeDetailsViewModel(private val recipesRepository: RecipesRepository): ViewModel() {

    private val _fetchDetails = MutableLiveData<String>()
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

    val isFavoriteRecipe = Transformations.switchMap(_fetchDetails) {
        recipesRepository.isFavoriteRecipe(it)
    }

    //region Public methods

    fun fetchRecipeDetails(recipeID: String) {
        _fetchDetails.value = recipeID
    }

    fun fabClicked() {
        _fetchDetails.value?.let { recipeID ->
            isFavoriteRecipe.value?.let { isFavoriteRecipe ->
                if (isFavoriteRecipe) {
                    recipesRepository.removeFavoriteRecipe(recipeID)
                } else {
                    _details.value?.let { uiModel ->
                        recipesRepository.saveFavoriteRecipe(uiModel.recipeID, uiModel.title, uiModel.posterURL)
                    }
                }
            }
        }
    }

    //endregion

    private fun handleRecipeDetailsResponse(response: Resource<RecipeDetails>) {
        when(response.status) {
            Status.SUCCESS -> {
                val uimodel = map(response.data!!)
                _details.value = uimodel
            }
        }
    }

    private fun map(data: RecipeDetails): RecipeDetailsUIModel {
        return RecipeDetailsUIModel(data.id, data.title, data.posterURL, data.description, data.ingredients, data.steps)
    }


}