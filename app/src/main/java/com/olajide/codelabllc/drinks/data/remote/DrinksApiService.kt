package com.olajide.codelabllc.drinks.data.remote

import com.olajide.codelabllc.drinks.data.entity.DrinksDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksApiService {
    @GET("api/json/v1/1/search.php?")
    suspend fun getDrinks(@Query("f") letter: String): Response<DrinksDto>

}