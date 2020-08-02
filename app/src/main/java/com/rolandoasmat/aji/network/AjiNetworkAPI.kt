package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Plate
import retrofit2.Response

interface AjiNetworkAPI {

    fun fetchFeaturedPlate(): Response<Plate>

    fun fetchBreakfastPlates(): Response<List<Plate>>

    fun fetchDinnerPlates(): Response<List<Plate>>

    fun fetchAppetizerPlates(): Response<List<Plate>>

    fun fetchDessertPlates(): Response<List<Plate>>

    fun fetchDrinks(): Response<List<Plate>>
}