package com.rolandoasmat.aji.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel

class FavoritesViewModel : ViewModel() {

    private val _favoriteRecipes = MutableLiveData<MealsListUiModel>()
    val favoriteRecipes: LiveData<MealsListUiModel>
        get() = _favoriteRecipes

    /**
     * Fetch favorite recipes
     */
    fun fetch() {
        // TODO fetch from database

    }

}