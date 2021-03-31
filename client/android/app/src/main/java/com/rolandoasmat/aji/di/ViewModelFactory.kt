package com.rolandoasmat.aji.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolandoasmat.aji.recipe_details.RecipeDetailsViewModel
import com.rolandoasmat.aji.home.HomeViewModel
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.saved.SavedViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(recipesRepository) as T
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> SavedViewModel(recipesRepository) as T
            modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java) -> RecipeDetailsViewModel(recipesRepository) as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}