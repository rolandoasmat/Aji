package com.rolandoasmat.aji.network

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.rolandoasmat.aji.repositories.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val coroutineContextProvider: CoroutineContextProvider) {

    private val scope = CoroutineScope(coroutineContextProvider.main)

    private val result = MutableLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        scope.launch(coroutineContextProvider.io) {
            fetchFromNetwork()
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    private suspend fun fetchFromNetwork() {
        withContext(coroutineContextProvider.io) {
            try {
                val response = createCall().toDeferred().await()
                val data = response.data
                if (data == null || response.hasErrors()) {
                    // handle application errors
                    val errorMessage = response.errors?.first()?.message
                    onFetchFailed()
                    withContext(coroutineContextProvider.main) {
                        setValue(Resource.error(errorMessage?: "", null))
                    }
                } else {
                    val successResponse =
                        ApiSuccessResponse<RequestType>(
                            data,
                            null
                        )
                    val processed = processResponse(successResponse)
                    withContext(coroutineContextProvider.main) {
                        setValue(Resource.success(processed))
                    }
                }
            } catch (e: ApolloException) {
                // handle protocol errors
                onFetchFailed()
                withContext(coroutineContextProvider.main) {
                    setValue(Resource.error(e.message ?: "", null))
                }
            }
        }

    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected abstract fun processResponse(response: ApiSuccessResponse<RequestType>): ResultType

    @MainThread
    protected abstract fun createCall(): ApolloQueryCall<RequestType>
}