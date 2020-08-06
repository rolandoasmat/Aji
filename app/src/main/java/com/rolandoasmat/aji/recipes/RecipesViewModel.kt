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
    private val _breakfastPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.getBreakfastPlates()) {
        MutableLiveData(it)
    }
    private val _breakfast = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_breakfastPlates) {
            handleBreakfastMealsResponse(it)
        }
    }
    val breakfast: LiveData<MealsListUiModel>
        get() = _breakfast

    // Dinner
    private val _dinnerPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchDinnerPlates()) {
        MutableLiveData(it)
    }
    private val _dinner = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_dinnerPlates) {
            handleDinnerMealsResponse(it)
        }
    }
    val dinner: LiveData<MealsListUiModel>
        get() = _dinner


    // Appetizers
    private val _appetizersPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchAppetizerPlates()) {
        MutableLiveData(it)
    }
    private val _appetizers = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_appetizersPlates) {
            handleAppetizersMealsResponse(it)
        }
    }
    val appetizers: LiveData<MealsListUiModel>
        get() = _appetizers


    // Desserts
    private val _dessertPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchDessertPlates()) {
        MutableLiveData(it)
    }
    private val _desserts = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_dessertPlates) {
            handleDessertMealsResponse(it)
        }
    }
    val desserts: LiveData<MealsListUiModel>
        get() = _desserts


    // Drinks
    private val _drinksPlates: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(recipesRepository.fetchDrinks()) {
        MutableLiveData(it)
    }
    private val _drinks = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_drinksPlates) {
            handleDrinkMealsResponse(it)
        }
    }
    val drinks: LiveData<MealsListUiModel>
        get() = _drinks



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

    private fun handleDinnerMealsResponse(response: Resource<List<Recipe>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _dinner.value = map(data)
                }
            }
        }
    }

    private fun handleAppetizersMealsResponse(response: Resource<List<Recipe>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _appetizers.value = map(data)
                }
            }
        }
    }

    private fun handleDessertMealsResponse(response: Resource<List<Recipe>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _desserts.value = map(data)
                }
            }
        }
    }

    private fun handleDrinkMealsResponse(response: Resource<List<Recipe>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _drinks.value = map(data)
                }
            }
        }
    }

    private fun map(data: List<Recipe>): MealsListUiModel {
        val mapped = data.map { map(it) }
        return MealsListUiModel(mapped)
    }

    private fun map(data: Recipe): MealsListItemUiModel {
        return MealsListItemUiModel(data.thumbnailURL, data.title)

    }

    //endregion

}