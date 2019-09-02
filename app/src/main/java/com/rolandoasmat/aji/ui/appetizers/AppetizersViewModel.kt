package com.rolandoasmat.aji.ui.appetizers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel

class AppetizersViewModel : ViewModel() {

    val meals = MutableLiveData<MealsListUiModel>()

    /**
     * Fetch Appetizers
     */
    fun fetch() {
        // TODO fetch from network
        val ceviche = MealsListItemUiModel("", "Ceviche")
        val anticuchos = MealsListItemUiModel("", "Anticucho")
        val meals = listOf(ceviche, anticuchos)
        val mockResponse = MealsListUiModel(meals)
        this.meals.value = mockResponse
    }

}