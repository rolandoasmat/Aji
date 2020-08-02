package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Plate
import retrofit2.Response

class MockAjiNetworkAPI: AjiNetworkAPI {
    override fun fetchFeaturedPlate(): Response<Plate> {
        val lomoSaltado = Plate("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Lomo Saltado")
        return Response.success(lomoSaltado)
    }

    override fun fetchBreakfastPlates(): Response<List<Plate>> {
        val chicharron = Plate("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Sandwich de Chicharron")
        val tamal = Plate("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Tamal")
        val empanada = Plate("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Empanada")
        val plates = listOf(chicharron, tamal, empanada)
        return Response.success(plates)
    }

    override fun fetchDinnerPlates(): Response<List<Plate>> {
        val lomoSaltado = Plate("https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg", "Lomo Saltado")
        val ajiDeGallina = Plate("https://okdiario.com/img/2018/10/10/receta-de-aji-de-gallina-655x368.jpg", "Ají de Gallina")
        val tallarinesVerdes = Plate("https://i2.wp.com/decomidaperuana.com/wp-content/uploads/2018/04/tallarines-verdes-receta-peruana.png", "Tallarines Verdes")
        val arrozChaufa = Plate("https://www.gourmet.cl/wp-content/uploads/2019/04/Arroz-chaufa-edit-2.jpg", "Arroz Chaufa")
        val plates = listOf(lomoSaltado, ajiDeGallina, tallarinesVerdes, arrozChaufa)
        return Response.success(plates)

    }

    override fun fetchAppetizerPlates(): Response<List<Plate>> {
        val ceviche = Plate("https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg", "Ceviche")
        val anticuchos = Plate("https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg", "Anticuchos")
        val papaHuancaina = Plate("https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg", "Papa a la Huancaina")
        val causa = Plate("https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg", "Causa")
        val plates = listOf(ceviche, anticuchos, papaHuancaina, causa)
        return Response.success(plates)
    }

    override fun fetchDessertPlates(): Response<List<Plate>> {
        val alfajor = Plate("https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg", "Alfajor")
        val arrozConLeche = Plate("https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg", "Arroz con leche")
        val picarones = Plate("https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg", "Picarones")
        val mazamorra = Plate("https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg", "Mazamorra morada")
        val plates = listOf(alfajor, arrozConLeche, picarones, mazamorra)
        return Response.success(plates)
    }

    override fun fetchDrinks(): Response<List<Plate>> {
        val pisco = Plate("https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg", "Pisco sour")
        val chicha = Plate("https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg", "Chicha morrada")
        val plates = listOf(pisco, chicha)
        return Response.success(plates)
    }

}