package com.rolandoasmat.aji.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.rolandoasmat.aji.BuildConfig
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AjiApolloClient @Inject constructor() {

    private val apolloClient: ApolloClient
        get() {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val builder = original.newBuilder().method(original.method(), original.body())
                    builder.header(AUTH_HEADER_PARAM, BuildConfig.API_KEY)
                    chain.proceed(builder.build())
                }
                .build()
            return ApolloClient.builder()
                .okHttpClient(httpClient)
                .serverUrl(BuildConfig.SERVER_URL)
                .build()
            }

    fun <D: Operation.Data, T, V: Operation.Variables> query(query: Query<D, T, V>): ApolloQueryCall<T> {
        return apolloClient.query(query)
    }

    companion object {
        private const val AUTH_HEADER_PARAM = "x-api-key"
    }

}