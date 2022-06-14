package com.olajide.codelabllc.drinks.data.entity

data class DrinksDto(
    val drinks: List<Drink>
)
//The below Data transfer object picks only the needed parameters and pass to DrinksDetail for UI consumption.
fun Drink.toDrink(): DrinksDetail {
    return DrinksDetail(
        strCategory = strCategory,
        strGlass =strGlass,
        strImageSource = strImageSource,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strDrink = strDrink,
        strInstructions = strInstructions,
        strAlcoholic = strAlcoholic
    )
}