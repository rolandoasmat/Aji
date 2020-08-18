package com.rolandoasmat.aji.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.repositories.RecipesRepository
import com.rolandoasmat.aji.recipes.RecipesUIModel

class SavedViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _saved = Transformations.map(recipesRepository.loadFavoriteRecipes()) {
        it.map { entry ->
            RecipesUIModel.Entry(entry.recipeID, entry.name, entry.posterURL)
        }
    }
    val saved: LiveData<List<RecipesUIModel.Entry>>
        get() = _saved

}