package com.rolandoasmat.aji.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel

class HomeViewModel : ViewModel() {

    val meals = MutableLiveData<MealsListUiModel>()

    /**
     * Fetch recipes
     */
    fun fetch() {
        // TODO fetch from network
        val lomoSaltado = MealsListItemUiModel("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Lomo Saltado")
        val ajiDeGallina = MealsListItemUiModel("https://okdiario.com/img/2018/10/10/receta-de-aji-de-gallina-655x368.jpg", "Ají de Gallina")
        val tallarinesVerdes = MealsListItemUiModel("https://i2.wp.com/decomidaperuana.com/wp-content/uploads/2018/04/tallarines-verdes-receta-peruana.png", "Tallarines Verdes")
        val arrozChaufa = MealsListItemUiModel("https://www.gourmet.cl/wp-content/uploads/2019/04/Arroz-chaufa-edit-2.jpg", "Arroz Chaufa")
        val entrees = listOf(lomoSaltado, ajiDeGallina, tallarinesVerdes, arrozChaufa)

        val ceviche = MealsListItemUiModel("https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg", "Ceviche")
        val anticuchos = MealsListItemUiModel("https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg", "Anticuchos")
        val papaHuancaina = MealsListItemUiModel("https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg", "Papa a la Huancaina")
        val causa = MealsListItemUiModel("https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg", "Causa")
        val appetizers = listOf(ceviche, anticuchos, papaHuancaina, causa)

        val meals = entrees + appetizers
        val mockResponse = MealsListUiModel(meals)
        this.meals.value = mockResponse
    }

}