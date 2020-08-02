package com.rolandoasmat.aji.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolandoasmat.aji.mealslist.MealsListUiModel

class SavedViewModel : ViewModel() {

    private val _saved = MutableLiveData<MealsListUiModel>()
    val saved: LiveData<MealsListUiModel>
        get() = _saved

    /**
     * Fetch saved recipes
     */
    fun fetch() {
        // TODO fetch from database

    }

}