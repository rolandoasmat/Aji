package com.rolandoasmat.aji.services

/**
 * Service to fetch data via network
 */
interface NetworkApiService {

    /**
     * Fetch list of appetizers
     */
    fun fetchAppetizers()

    /**
     * Fetch list of entrees
     */
    fun fetchEntrees()

    /**
     * Fetch the meal's details
     */
    fun fetchMealDetails(mealID: Int)

    /**
     * Fetch meal's step by step guided preparation
     */
    fun fetchMealGuidedPreparation(mealID: Int)
}