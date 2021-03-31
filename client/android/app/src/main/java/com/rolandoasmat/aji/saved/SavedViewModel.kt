package com.rolandoasmat.aji.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.repositories.RecipesRepository

class SavedViewModel(recipesRepository: RecipesRepository) : ViewModel() {

    private val _uiModel = Transformations.map(recipesRepository.loadFavoriteRecipes()) {
        SavedUiModel.fromRecipes(it)
    }

    val uiModel: LiveData<SavedUiModel>
        get() = _uiModel

}