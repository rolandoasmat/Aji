package com.rolandoasmat.aji.home

import androidx.lifecycle.*
import com.rolandoasmat.aji.model.HomeScreenData
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.network.Status
import com.rolandoasmat.aji.model.Recipes

class HomeViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _fetchRecipes = MutableLiveData<Unit>()
    private val _recipesSource = Transformations.switchMap(_fetchRecipes) {
        recipesRepository.fetchHomeScreenRecipes()
    }
    private val _uiModel = MediatorLiveData<HomeUIModel>().apply {
        addSource(_recipesSource) {
            handleRecipesResponse(it)
        }
    }
    val uiModel: LiveData<HomeUIModel>
        get() = _uiModel

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?>
        get() = _error

    fun fetch() {
        _fetchRecipes.value = Unit
    }

    fun refresh() {
        fetch()
    }

    fun errorHandled() {
        _error.value = null
    }

    private fun handleRecipesResponse(response: Resource<HomeScreenData>) {
        _loading.value = response.status == Status.LOADING
        response.data?.let { recipes ->
//            TODO create updated UI model
//            val  grid = RecipesGridViewUiModel.from(recipes.items)
//            _uiModel.value = HomeUIModel(grid)
        }
        response.message?.let { errorMessage ->
            _error.value = errorMessage

        }
    }
}