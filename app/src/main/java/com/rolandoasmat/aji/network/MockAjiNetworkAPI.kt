package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import retrofit2.Response

class MockAjiNetworkAPI: AjiNetworkAPI {

    override fun fetchBreakfastPlates(): Response<List<Recipe>> {
        val chicharron = Recipe(
            11,
            "Sandwich de Chicharron",
            "https://i0.wp.com/exitosanoticias.pe/v1/wp-content/uploads/2020/04/panc2.jpg",
        "https://i0.wp.com/exitosanoticias.pe/v1/wp-content/uploads/2020/04/panc2.jpg")
        val tamal = Recipe(
            12,
            "Tamal",
            "https://www.comeperuano.pe/wp-content/uploads/2020/03/tamales-2.jpg",
            "https://www.comeperuano.pe/wp-content/uploads/2020/03/tamales-2.jpg")
        val empanada = Recipe(
            13,
            "Empanada",
            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/delish-190807-empanadas-0105-landscape-pf-1566245422.jpg?crop=1.00xw:0.752xh;0,0.180xh&resize=1200:*",
            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/delish-190807-empanadas-0105-landscape-pf-1566245422.jpg?crop=1.00xw:0.752xh;0,0.180xh&resize=1200:*")
        val plates = listOf(chicharron, tamal, empanada)
        return Response.success(plates)
    }

    override fun fetchDinnerPlates(): Response<List<Recipe>> {
        val lomoSaltado = Recipe(
            21,
            "Lomo Saltado",
            "https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg",
            "https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg")
        val ajiDeGallina = Recipe(
            22,
            "Ají de Gallina",
            "https://okdiario.com/img/2018/10/10/receta-de-aji-de-gallina-655x368.jpg",
            "https://okdiario.com/img/2018/10/10/receta-de-aji-de-gallina-655x368.jpg")
        val tallarinesVerdes = Recipe(
            23,
            "Tallarines Verdes",
            "https://i2.wp.com/decomidaperuana.com/wp-content/uploads/2018/04/tallarines-verdes-receta-peruana.png",
            "https://i2.wp.com/decomidaperuana.com/wp-content/uploads/2018/04/tallarines-verdes-receta-peruana.png")
        val arrozChaufa = Recipe(
            24,
            "Arroz Chaufa",
            "https://www.gourmet.cl/wp-content/uploads/2019/04/Arroz-chaufa-edit-2.jpg",
            "https://www.gourmet.cl/wp-content/uploads/2019/04/Arroz-chaufa-edit-2.jpg")
        val arrozConPollo = Recipe(
            25,
            "Arroz con Pollo",
            "https://www.recetips.com/uploads/recetas_ce89af3170d309eea9addbf67a3bc639.jpg",
            "https://www.recetips.com/uploads/recetas_ce89af3170d309eea9addbf67a3bc639.jpg")
        val plates = listOf(lomoSaltado, ajiDeGallina, tallarinesVerdes, arrozChaufa, arrozConPollo)
        return Response.success(plates)

    }

    override fun fetchAppetizerPlates(): Response<List<Recipe>> {
        val ceviche = Recipe(
            31,
            "Ceviche",
            "https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg",
            "https://cevicheperuano.net/wp-content/uploads/2018/12/ceviche-de-pescado_700x465.jpg")
        val anticuchos = Recipe(
            32,
            "Anticuchos",
            "https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg",
            "https://e.rpp-noticias.io/normal/2018/11/12/252825_709013.jpg")
        val papaHuancaina = Recipe(
            33,
            "Papa a la Huancaina",
            "https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg",
            "https://www.whats4eats.com/files/vegetables-papa-huancaina-wikimedia-AgainErick-4x3.jpg")
        val causa = Recipe(
            33,
            "Causa",
            "https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg",
            "https://www.seriouseats.com/2018/07/20180702-peruvian-causa-vicky-wasik-21-625x469.jpg")
        val plates = listOf(ceviche, anticuchos, papaHuancaina, causa)
        return Response.success(plates)
    }

    override fun fetchDessertPlates(): Response<List<Recipe>> {
        val alfajor = Recipe(
            41,
            "Alfajor",
            "https://jameaperu.com/wp-content/uploads/2020/03/alfajores_700x465.jpg",
            "https://jameaperu.com/wp-content/uploads/2020/03/alfajores_700x465.jpg")
        val arrozConLeche = Recipe(
            42,
            "Arroz con Leche",
            "https://carolinarice.com/wp-content/uploads/2019/05/Arroz-Con-Leche-Cinnamon-Sticks.jpg",
            "https://carolinarice.com/wp-content/uploads/2019/05/Arroz-Con-Leche-Cinnamon-Sticks.jpg")
        val picarones = Recipe(
            43,
            "Picarones",
            "https://www.vikingcruises.com/oceans/images/Peruvian_Picarones_1009x385_tcm13-157153.jpg",
            "https://www.comeperuano.pe/wp-content/uploads/2020/02/Picarones-peruanos.jpg")
        val mazamorra = Recipe(
            44,
            "Mazamorra Morada",
            "https://perudelights.com/wp-content/uploads/2012/07/DSC058141-1024x565.jpg",
            "https://perudelights.com/wp-content/uploads/2012/07/DSC058141-1024x565.jpg")
        val plates = listOf(alfajor, arrozConLeche, picarones, mazamorra)
        return Response.success(plates)
    }

    override fun fetchDrinks(): Response<List<Recipe>> {
        val pisco = Recipe(
            51,
            "Pisco Sour",
            "https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/pisco-sour.jpg?itok=qwqQe8hb",
            "https://www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe_images/pisco-sour.jpg?itok=qwqQe8hb")
        val chicha = Recipe(
            52,
            "Chicha Morrada",
            "https://smartsexypaleo.com/wp-content/uploads/2020/05/chicha-morada-purple-sweet-traditional-peruvian-corn-drink_101123-465.jpg",
            "https://smartsexypaleo.com/wp-content/uploads/2020/05/chicha-morada-purple-sweet-traditional-peruvian-corn-drink_101123-465.jpg")
        val plates = listOf(pisco, chicha)
        return Response.success(plates)
    }

    override fun fetchRecipeDetails(id: Int): Response<RecipeDetails> {
        val title = "Test Recipe"
        val posterURL = "https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg"
        val description = "A delicious meal that will make wildest dreams come true. Super duper extra long description of the meal you are about to prepare."
        val ingredients = listOf("1 clove of garlic", "the tear of a crocodile", "5 grains of rice", "Kobe beef fed salmon", "apple", "1 clove of garlic", "the tear of a crocodile", "5 grains of rice", "Kobe beef fed salmon", "apple", "1 clove of garlic", "the tear of a crocodile", "5 grains of rice", "Kobe beef fed salmon", "apple")
        val steps = listOf("1 clove of garlic", "the tear of a crocodile", "5 grains of rice", "Kobe beef fed salmon", "apple")
        return Response.success(RecipeDetails(id,  title, posterURL, description, ingredients, steps))
    }

}