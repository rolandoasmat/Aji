package com.rolandoasmat.aji.home

import androidx.lifecycle.*
import com.rolandoasmat.aji.MealsRepository
import com.rolandoasmat.aji.Resource
import com.rolandoasmat.aji.Status
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import com.rolandoasmat.aji.model.Plate

class HomeViewModel(private val mealsRepository: MealsRepository) : ViewModel() {

    private val fetchMeals = MutableLiveData<Unit>()
    private val _fetchMeals: LiveData<Resource<List<Plate>>> = Transformations.switchMap(mealsRepository.getMeals()) {
        MutableLiveData(it)
    }

    private val _meals = MediatorLiveData<MealsListUiModel>().apply {
        addSource(_fetchMeals) {
            handleFetchMealsResponse(it)
        }
    }

    val meals: LiveData<MealsListUiModel>
        get() = _meals

    /**
     * Fetch recipes
     */
    fun fetch() {
        fetchMeals.value = null
    }

    private fun handleFetchMealsResponse(data: Resource<List<Plate>>) {
        when(data.status) {
            Status.SUCCESS -> {
                data.data?.map { MealsListItemUiModel(it.imageURL, it.title) }?.let { uiModels ->
                    val uiModel = MealsListUiModel(uiModels)
                    _meals.value = uiModel
                }
            }
        }

    }

}