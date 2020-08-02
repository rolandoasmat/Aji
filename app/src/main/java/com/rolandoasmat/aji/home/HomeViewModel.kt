package com.rolandoasmat.aji.home

import androidx.lifecycle.*
import com.rolandoasmat.aji.MealsRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import com.rolandoasmat.aji.model.Plate

class HomeViewModel(mealsRepository: MealsRepository) : ViewModel() {

    private val fetchMeals = MutableLiveData<Unit>()

    // Featured
    private val _featuredPlate: LiveData<Resource<Plate>> = Transformations.switchMap(mealsRepository.fetchFeaturedPlate()) {
        MutableLiveData(it)
    }
    private val _featured = MediatorLiveData<MealsListItemUiModel>().apply {
        addSource(_featuredPlate) {
            handleFeaturedMealResponse(it)
        }
    }
    val featured: LiveData<MealsListItemUiModel>
        get() = _featured


    // Breakfast
    private val _breakfastPlates: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.getBreakfastPlates()) {
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
    private val _dinnerPlates: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.fetchDinnerPlates()) {
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
    private val _appetizersPlates: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.fetchAppetizerPlates()) {
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
    private val _dessertPlates: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.fetchDessertPlates()) {
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
    private val _drinksPlates: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.fetchDrinks()) {
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

    private fun handleFeaturedMealResponse(response: Resource<Plate>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _featured.value = map(data)
                }
            }
        }
    }

    private fun handleBreakfastMealsResponse(response: Resource<List<Plate>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _breakfast.value = map(data)
                }
            }
        }
    }

    private fun handleDinnerMealsResponse(response: Resource<List<Plate>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _dinner.value = map(data)
                }
            }
        }
    }

    private fun handleAppetizersMealsResponse(response: Resource<List<Plate>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _appetizers.value = map(data)
                }
            }
        }
    }

    private fun handleDessertMealsResponse(response: Resource<List<Plate>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _desserts.value = map(data)
                }
            }
        }
    }

    private fun handleDrinkMealsResponse(response: Resource<List<Plate>>) {
        when(response.status) {
            Status.SUCCESS -> {
                response.data?.let { data ->
                    _drinks.value = map(data)
                }
            }
        }
    }

    private fun map(data: List<Plate>): MealsListUiModel {
        val mapped = data.map { map(it) }
        return MealsListUiModel(mapped)
    }

    private fun map(data: Plate): MealsListItemUiModel {
        return MealsListItemUiModel(data.backdropURL, data.title)

    }

    //endregion

}