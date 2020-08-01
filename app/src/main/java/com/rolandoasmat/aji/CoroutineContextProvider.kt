package com.rolandoasmat.aji

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider @Inject constructor() {
    val main: CoroutineContext = Dispatchers.Main
    val io: CoroutineContext = Dispatchers.IO
    val default: CoroutineContext = Dispatchers.Default
}