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

    fun getBreakfastPlates(): LiveData<Resource<List<Plate>>> {
        return object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>) = response.body
            override fun createCall(): Response<List<Plate>> = api.fetchBreakfastPlates()
        }.asLiveData()
    }

}