package com.rolandoasmat.aji.recipe_details

import androidx.lifecycle.*
import com.rolandoasmat.aji.ingredients.IngredientUIModel
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.network.Status
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.steps.StepListItemUIModel

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
        val ingredients = data.ingredients.map { IngredientUIModel(it.name, it.amount) }
        return RecipeDetailsUIModel(
            data.id,
            data.title,
            data.posterURL,
            data.description,
            data.cookingTime ?: "--",
            data.servingSize ?: "--",
            ingredients,
            data.steps.map { StepListItemUIModel(it.description) })
    }
}