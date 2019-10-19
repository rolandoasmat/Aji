package com.rolandoasmat.aji.mealslist

/**
 * Callbacks for a displayed item of [MealsListAdapter]
 */
interface MealListItemCallbacks {

    /**
     * Meal item image was tapped
     *
     * @param index Index of item who's image was pressed
     */
    fun onImageTapped(index: Int)
}