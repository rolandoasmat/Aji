package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Recipe
import com.rolandoasmat.aji.model.RecipeDetails
import retrofit2.Response

class MockAjiNetworkAPI: AjiNetworkAPI {

    override fun fetchBreakfastPlates(): Response<List<Recipe>> {
        return Response.success(breakfastRecipes)
    }

    override fun fetchDinnerPlates(): Response<List<Recipe>> {
       return Response.success(entreeRecipes)
    }

    override fun fetchAppetizerPlates(): Response<List<Recipe>> {
        return Response.success(appetizerRecipes)
    }

    override fun fetchDessertPlates(): Response<List<Recipe>> {
        return Response.success(dessertRecipes)
    }

    override fun fetchDrinks(): Response<List<Recipe>> {
        return Response.success(drinkRecipes)
    }

    override fun fetchRecipeDetails(id: Int): Response<RecipeDetails> {
        val title = "Test Recipe"
        val posterURL = "https://images-gmi-pmc.edge-generalmills.com/7bbcdd09-3380-4117-951b-1c8098ca984e.jpg"
        val description = "A delicious meal that will make wildest dreams come true. Super duper extra long description of the meal you are about to prepare."
        return Response.success(RecipeDetails(id,  title, posterURL, description, lomoSaltadoIngredients, lomoSaltadoSteps))
    }

    companion object {

        private val breakfastRecipes: List<Recipe>
            get() {
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
                return listOf(chicharron, tamal, empanada)
            }

        private val entreeRecipes: List<Recipe>
            get() {
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
                return listOf(lomoSaltado, ajiDeGallina, tallarinesVerdes, arrozChaufa, arrozConPollo)
            }

        private val appetizerRecipes: List<Recipe>
            get() {
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
                return listOf(ceviche, anticuchos, papaHuancaina, causa)
            }

        private val dessertRecipes: List<Recipe>
            get() {
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
                return listOf(alfajor, arrozConLeche, picarones, mazamorra)
            }

        private val drinkRecipes: List<Recipe>
            get() {
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
                return listOf(pisco, chicha)
            }

        private val lomoSaltadoIngredients = listOf(
            "1 lb of sirlion steak",
            "4 small tomatoes",
            "1 large red onion",
            "2 cups of rice",
            "1 bag of frech fries",
            "3 cloves of garlic",
            "1 TBSP of soy sauce"
        )

        private val lomoSaltadoSteps = listOf(
            "Set rice to cook",
            "Preheat oven and place bag of fries",
            "Prep steak by cutting into strips",
            "Prep tomato and onion by slicing"
        )

    }

}