package com.rolandoasmat.aji.network

import com.rolandoasmat.aji.model.Plate
import retrofit2.Response

interface AjiNetworkAPI {

    fun fetchPlates(): Response<List<Plate>>
}