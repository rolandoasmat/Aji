package com.rolandoasmat.aji.recipe_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngredientsViewModel: ViewModel() {

    private val _ingredients = MutableLiveData<List<String>>()
    val ingredients: LiveData<List<String>>
        get() = _ingredients


    fun setData(data: List<String>) {
        _ingredients.value = data
    }
}