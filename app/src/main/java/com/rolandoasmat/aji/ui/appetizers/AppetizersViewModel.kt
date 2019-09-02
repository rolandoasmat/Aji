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
        val ceviche = MealsListItemUiModel("https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg", "Ceviche")
        val anticuchos = MealsListItemUiModel("https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg", "Anticuchos")
        val papaHuancaina = MealsListItemUiModel("https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg", "Papa a la Huancaina")
        val causa = MealsListItemUiModel("https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg", "Causa")
        val meals = listOf(ceviche, anticuchos, papaHuancaina, causa)
        val mockResponse = MealsListUiModel(meals)
        this.meals.value = mockResponse
    }

}