package com.rolandoasmat.aji.home

import androidx.lifecycle.*
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.network.Status
import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.ui.RecipesGridViewUiModel

class HomeViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _fetchRecipes = MutableLiveData<Unit>()
    private val _recipesSource: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(_fetchRecipes) {
        recipesRepository.fetchRecipes()
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

    private fun handleRecipesResponse(response: Resource<List<Recipe>>) {
        _loading.value = response.status == Status.LOADING
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    val  grid = RecipesGridViewUiModel.from(data)
                    _uiModel.value = HomeUIModel(grid)
                }
            }
            Status.ERROR -> {
                _error.value = response.message
            }
        }
    }

}