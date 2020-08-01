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

    fun getMeals(): LiveData<Resource<List<Plate>>> {
        val resource = object : NetworkBoundResource<List<Plate>, List<Plate>>(coroutineContextProvider) {
            override fun processResponse(response: ApiSuccessResponse<List<Plate>>): List<Plate> {
                return response.body
            }
            override fun createCall(): Response<List<Plate>> {
                return api.fetchPlates()
            }
        }
        return resource.asLiveData()

    }
}