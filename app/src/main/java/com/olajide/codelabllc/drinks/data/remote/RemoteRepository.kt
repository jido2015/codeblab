package com.olajide.codelabllc.drinks.data.remote

import com.olajide.codelabllc.drinks.data.entity.DrinksDto
import com.olajide.codelabllc.drinks.domain.repository.DrinksRepository
import com.olajide.codelabllc.drinks.resource.DataHandler
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val drinkApiService: ProvideDrinksApiService,) : DrinksRepository {
    override suspend fun getDrinks( search: String
    ): DataHandler<DrinksDto> {

        return try {
            val response = drinkApiService.getDrinks(search)
            val result = response.body()
            if (response.isSuccessful && result != null) {

                DataHandler.Success(result)
            } else {
                DataHandler.Failure("Unable to get drinks list")
            }
        } catch (e: Exception) {
            DataHandler.Exception(e.localizedMessage)
    }
    }

}
