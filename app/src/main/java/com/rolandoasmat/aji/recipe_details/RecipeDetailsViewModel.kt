package com.rolandoasmat.aji.recipe_details

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
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

    val details: LiveData<RecipeDetailsUIModel?>
        get() = _details

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    val isFavoriteRecipe = Transformations.switchMap(_fetchDetails) {
        recipesRepository.isFavoriteRecipe(it)
    }

    //region Public methods

    fun fetchRecipeDetails(recipeID: String) {
        _fetchDetails.value = recipeID
    }

    fun refresh() {
        _fetchDetails.value.let {
            _fetchDetails.value = it
        }
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

    fun errorHandled() {
        _errorMessage.value = null
    }

    //endregion

    private fun handleRecipeDetailsResponse(response: Resource<RecipeDetails>) {
        _loading.value = response.status == Status.LOADING
        if (response.status == Status.ERROR) {
            _errorMessage.value = response.message
        }
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