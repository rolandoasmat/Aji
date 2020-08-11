package com.rolandoasmat.aji.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.RecipesRepository
import com.rolandoasmat.aji.mealslist.MealsListItemUiModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel

class SavedViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _saved = Transformations.map(recipesRepository.loadFavoriteRecipes()) {
        val recipes = it.map { entry ->
            MealsListItemUiModel(entry.recipeID, entry.posterURL, entry.name)
        }
        MealsListUiModel(recipes)
    }
    val saved: LiveData<MealsListUiModel>
        get() = _saved

}