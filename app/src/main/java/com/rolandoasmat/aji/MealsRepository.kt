package com.rolandoasmat.aji

import androidx.lifecycle.LiveData
import com.rolandoasmat.aji.model.Plate
import com.rolandoasmat.aji.model.RecipeDetails
import com.rolandoasmat.aji.network.AjiNetworkAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MealsRepository @Inject constructor(
    private val api: AjiNetworkAPI,
    private val coroutineContextProvider: CoroutineContextProvider) {

    fun fetchFeaturedPlate(): LiveData<Resource<Plate>> {
        return object : NetworkBoundResource<Plate, Plate>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<Plate>) = response.body
            override fun createCall() = api.fetchFeaturedPlate()
        }.asLiveData()
    }

    fun getBreakfastPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall() = api.fetchBreakfastPlates()
        }.asLiveData()
    }

    fun fetchDinnerPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall() = api.fetchDinnerPlates()
        }.asLiveData()
    }

    fun fetchAppetizerPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall() = api.fetchAppetizerPlates()
        }.asLiveData()
    }

    fun fetchDessertPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall() = api.fetchDessertPlates()
        }.asLiveData()
    }

    fun fetchDrinks(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall() = api.fetchDrinks()
        }.asLiveData()
    }

    fun fetchRecipeDetails(id: Int): LiveData<Resource<RecipeDetails>> {
        return object: NetworkBoundResource<RecipeDetails, RecipeDetails>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<RecipeDetails>) = response.body
            override fun createCall() = api.fetchRecipeDetails(id)
        }.asLiveData()
    }

}