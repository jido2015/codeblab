package com.olajide.codelabllc.drinks.resource
import kotlinx.coroutines.CoroutineDispatcher

interface DispatchProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

}