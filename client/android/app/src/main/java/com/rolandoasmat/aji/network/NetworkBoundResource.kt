package com.rolandoasmat.aji.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.exception.ApolloException
import com.rolandoasmat.aji.repositories.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Generic Flow based network request resource
 */
abstract class NetworkBoundResource<ResultType, ResponseType> @MainThread constructor(
    private val coroutineContextProvider: CoroutineContextProvider
    ) {

    private val scope = CoroutineScope(coroutineContextProvider.main)
    private val result = MutableLiveData<Resource<ResultType>>()

    fun asFlow() = flow<Resource<ResultType>> {
        withContext(coroutineContextProvider.io) { // vs scope.launch(coroutineContextProvider.io)?
            emit(Resource.loading())
            try {
                val response = createCall()
                when (response) {
                    is ApiSuccessResponse -> {
                        val data = response.data
                        val processed = processResponse(data)
                        emit(Resource.success(processed))
                    }
                    is ApiErrorResponse -> {
                        emit(Resource.error(response.errorMessage))
                    }
                }
            } catch (e: ApolloException) {
                emit(Resource.error(e.message ?: ""))
            }
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response: ResponseType): ResultType

    @MainThread
    protected abstract suspend fun createCall(): ApiResponse<ResponseType>
}