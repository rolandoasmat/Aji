package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.network.AjiApolloClient
import com.rolandoasmat.aji.network.AjiNetworkService
import com.rolandoasmat.aji.network.AjiNetworkServiceProd
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideContext(ajiApolloClient: AjiApolloClient): AjiNetworkService {
        return AjiNetworkServiceProd(ajiApolloClient)
    }
}