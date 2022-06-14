package com.olajide.codelabllc.drinks.resource

sealed class DataHandler<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : DataHandler<T>(data, null)
    class Failure<T>(message: String) : DataHandler<T>(null, message)
    class Exception<T>(message: String?) : DataHandler<T>(null, message)
}