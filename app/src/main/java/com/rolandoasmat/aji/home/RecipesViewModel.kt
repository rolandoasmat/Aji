package com.rolandoasmat.aji.home

import androidx.lifecycle.*
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.network.Status
import com.rolandoasmat.aji.model.Recipe

class RecipesViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _fetchRecipes = MutableLiveData<Unit>()
    private val _recipesSource: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(_fetchRecipes) {
        recipesRepository.fetchRecipes()
    }
    private val _recipes = MediatorLiveData<RecipesUIModel>().apply {
        addSource(_recipesSource) {
            handleRecipesResponse(it)
        }
    }
    val recipes: LiveData<RecipesUIModel>
        get() = _recipes

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
                    _recipes.value = RecipesUIModel.from(data)
                }
            }
            Status.ERROR -> {
                _error.value = response.message
            }
        }
    }

}