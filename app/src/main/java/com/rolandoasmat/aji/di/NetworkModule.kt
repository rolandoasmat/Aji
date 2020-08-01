package com.rolandoasmat.aji.di

import com.rolandoasmat.aji.network.AjiNetworkAPI
import com.rolandoasmat.aji.network.MockAjiNetworkAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideApiClient(): AjiNetworkAPI {
        return MockAjiNetworkAPI()
    }
}