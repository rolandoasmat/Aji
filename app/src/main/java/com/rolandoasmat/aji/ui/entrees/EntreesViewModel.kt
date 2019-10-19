package com.rolandoasmat.aji.ui.entrees

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import com.rolandoasmat.ajinetworksdk.AjiNetworkAPI

class EntreesViewModel : ViewModel() {

    val meals = MutableLiveData<MealsListUiModel>()

    /**
     * Fetch Appetizers
     */
    fun fetch() {
        // TODO fetch from network
        val lomoSaltado = MealsListItemUiModel("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Lomo Saltado")
        val ajiDeGallina = MealsListItemUiModel("https://okdiario.com/img/2018/10/10/receta-de-aji-de-gallina-655x368.jpg", "Ají de Gallina")
        val tallarinesVerdes = MealsListItemUiModel("https://i2.wp.com/decomidaperuana.com/wp-content/uploads/2018/04/tallarines-verdes-receta-peruana.png", "Tallarines Verdes")
        val arrozChaufa = MealsListItemUiModel("https://www.gourmet.cl/wp-content/uploads/2019/04/Arroz-chaufa-edit-2.jpg", "Arroz Chaufa")
        val meals = listOf(lomoSaltado, ajiDeGallina, tallarinesVerdes, arrozChaufa)
        val mockResponse = MealsListUiModel(meals)
        this.meals.value = mockResponse
    }

}