package com.rolandoasmat.aji.mealslist

/**
 * Callbacks for a displayed item of [MealsListAdapter]
 */
interface MealListItemCallbacks {

    /**
     * Meal item image was tapped
     *
     * @param recipeID Recipe id of the image that was pressed
     */
    fun onImageTapped(recipeID: String)
}