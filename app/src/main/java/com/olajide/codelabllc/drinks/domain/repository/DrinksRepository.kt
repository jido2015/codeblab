package com.olajide.codelabllc.drinks.domain.repository

import com.olajide.codelabllc.drinks.data.entity.DrinksDto
import com.olajide.codelabllc.drinks.resource.DataHandler

interface DrinksRepository {

    suspend fun getDrinks(search: String): DataHandler<DrinksDto>
}