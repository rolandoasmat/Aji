package com.rolandoasmat.aji.recipes

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
import com.rolandoasmat.aji.model.Recipe

class RecipesViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val fetchMeals = MutableLiveData<Unit>()

    // Breakfast
    private val _breakfastPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchRecipes()) {
        MutableLiveData(it)
    }
    private val _breakfast = MediatorLiveData<RecipesUIModel>().apply {
        addSource(_breakfastPlates) {
            handleBreakfastMealsResponse(it)
        }
    }
    val breakfast: LiveData<RecipesUIModel>
        get() = _breakfast

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    /**
     * Fetch recipes
     */
    fun fetch() {
        fetchMeals.value = null
    }

    //region Private
    private fun handleBreakfastMealsResponse(response: Resource<List<Recipe>>) {
        _loading.value = response.status == Status.LOADING
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _breakfast.value = map(data)
                }
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
                1 -> {
                    val entry = RecipesUIModel.Entry(value[0].recipeID, value[0].title, value[0].thumbnailURL)
                    val section = RecipesUIModel.Section.SingleCard(key, entry)
                    sections.add(section)
                }
                else -> {
                    val entries = value.map {
                        RecipesUIModel.Entry(it.recipeID, it.title, it.thumbnailURL)
                    }
                    val section = if (value.count() <= 4) {
                        RecipesUIModel.Section.Grid(key, entries)
                    } else {
                        RecipesUIModel.Section.HorizontalRow(key, entries)
                    }
                    sections.add(section)
                }
            }
        }
        return RecipesUIModel(sections)
    }
    //endregion

}