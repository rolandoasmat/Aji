package com.rolandoasmat.aji

import androidx.lifecycle.LiveData
import com.rolandoasmat.aji.model.Plate
import com.rolandoasmat.aji.network.AjiNetworkAPI
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MealsRepository @Inject constructor(
    private val api: AjiNetworkAPI,
    private val coroutineContextProvider: CoroutineContextProvider) {

    fun fetchFeaturedPlate(): LiveData<Resource<Plate>> {
        return object : NetworkBoundResource<Plate, Plate>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<Plate>) = response.body
            override fun createCall(): Response<Plate> = api.fetchFeaturedPlate()
        }.asLiveData()
    }

    fun getBreakfastPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchBreakfastPlates()
        }.asLiveData()
    }

    fun fetchDinnerPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchDinnerPlates()
        }.asLiveData()
    }

    fun fetchAppetizerPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchAppetizerPlates()
        }.asLiveData()
    }

    fun fetchDessertPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchDessertPlates()
        }.asLiveData()
    }

    fun fetchDrinks(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchDrinks()
        }.asLiveData()
    }

}