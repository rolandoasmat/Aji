package com.rolandoasmat.aji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolandoasmat.aji.home.RecipesViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val mealsRepository: MealsRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RecipesViewModel::class.java) -> RecipesViewModel(mealsRepository) as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}