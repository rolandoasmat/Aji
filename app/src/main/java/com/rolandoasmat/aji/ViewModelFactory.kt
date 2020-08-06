package com.rolandoasmat.aji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolandoasmat.aji.recipe_details.IngredientsViewModel
import com.rolandoasmat.aji.recipe_details.RecipeDetailsViewModel
import com.rolandoasmat.aji.recipes.RecipesViewModel
import com.rolandoasmat.aji.saved.SavedViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val recipesRepository: RecipesRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RecipesViewModel::class.java) -> RecipesViewModel(recipesRepository) as T
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> SavedViewModel() as T
            modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java) -> RecipeDetailsViewModel(recipesRepository) as T
            modelClass.isAssignableFrom(IngredientsViewModel::class.java) -> IngredientsViewModel() as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}