package com.rolandoasmat.aji.recipes

import androidx.lifecycle.*
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.network.Resource
import com.rolandoasmat.aji.network.Status
import com.rolandoasmat.aji.model.Recipe

class RecipesViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    // Breakfast
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

    //region Public

    fun fetch() {
        _fetchRecipes.value = Unit
    }

    fun refresh() {
        fetch()
    }

    fun errorHandled() {
        _error.value = null
    }

    //endregion

    //region Private
    private fun handleRecipesResponse(response: Resource<List<Recipe>>) {
        _loading.value = response.status == Status.LOADING
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _recipes.value = map(data)
                }
            }
            Status.ERROR -> {
                _error.value = response.message
            }
        }
    }

    private fun map(data: List<Recipe>): RecipesUIModel {
        val groups = data.sortedBy { it.recipeID.toInt() } .groupBy { it.sectionTitle }
        val sections = mutableListOf<RecipesUIModel.Section>()
        groups.forEach { action ->
            val key = action.key
            val value = action.value
            when (value.count()) {
                0 -> { } // no-op
                else -> {
                    val entries = value.map {
                        RecipesUIModel.Entry(it.recipeID, it.title, it.thumbnailURL)
                    }
                    val section = when (value.count()) {
                        1, 2 -> {
                            RecipesUIModel.Section.VerticalColumn(key, entries)
                        }
                        3, 4 -> {
                            RecipesUIModel.Section.Grid(key, entries)
                        }
                        else -> {
                            RecipesUIModel.Section.HorizontalRow(key, entries)
                        }
                    }
                    sections.add(section)
                }
            }
        }
        return RecipesUIModel(sections)
    }
    //endregion

}