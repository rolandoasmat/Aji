package com.rolandoasmat.aji.recipes

import androidx.lifecycle.*
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import com.rolandoasmat.aji.model.Recipe

class RecipesViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val fetchMeals = MutableLiveData<Unit>()

    // Breakfast
    private val _breakfastPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchRecipes()) {
        MutableLiveData(it)
    }
    private val _breakfast = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_breakfastPlates) {
            handleBreakfastMealsResponse(it)
        }
    }
    val breakfast: LiveData<MealsListUiModel>
        get() = _breakfast

    /**
     * Fetch recipes
     */
    fun fetch() {
        fetchMeals.value = null
    }

    //region Private

    private fun handleBreakfastMealsResponse(response: Resource<List<Recipe>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _breakfast.value = map(data)
                }
            }
        }
    }

    private fun map(data: List<Recipe>): MealsListUiModel {
        val mapped = data.map { map(it) }
        return MealsListUiModel(mapped)
    }

    private fun map(data: Recipe): MealsListItemUiModel {
        return MealsListItemUiModel(data.recipeID, data.thumbnailURL, data.title)
    }

    //endregion

}