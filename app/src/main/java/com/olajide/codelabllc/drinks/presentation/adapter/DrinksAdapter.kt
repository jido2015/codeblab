package com.olajide.codelabllc.drinks.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.olajide.codelabllc.databinding.DrinksItemBinding
import com.olajide.codelabllc.drinks.common.DrinksDiffUtils
import com.olajide.codelabllc.drinks.data.entity.Drink

class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {
    private var drinkList = ArrayList<Drink>()

    // stores and recycles views as they are scrolled off screen
    class DrinksViewHolder(private val binding: DrinksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind( drinksDetail: Drink) {
            binding.strCategory.text = "Cocktail category: "+drinksDetail.strCategory
            binding.strImageSource.load(drinksDetail.strDrinkThumb)
            binding.strIngredient1.text = "Ingredients 1: "+drinksDetail.strIngredient1
            binding.strIngredient2.text = "Ingredients 2: "+drinksDetail.strIngredient2
            binding.strIngredient3.text = "Ingredients 3: "+drinksDetail.strIngredient3
            binding.strDrink.text = "Type of drink: "+drinksDetail.strDrink
            binding.strInstructions.text = "Instructions: "+drinksDetail.strInstructions

            when (drinksDetail.strAlcoholic) {
                "Alcoholic" -> {
                    binding.strAlcoholic.text ="Is alcoholic: Yes"
                }
                else -> {
                    binding.strAlcoholic.text ="Is alcoholic: No"
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrinksViewHolder {

        return DrinksViewHolder(
            DrinksItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: DrinksViewHolder,
        position: Int
    ) {
        val currentItem = drinkList[position]
        holder.bind(currentItem)
    }


    override fun getItemCount(): Int = drinkList.size


    fun setItem(listOfDrinksItems: ArrayList<Drink>) {
        val drinksDiffUtil =
            DrinksDiffUtils(drinkList, listOfDrinksItems)
        val diffUtilResult = DiffUtil.calculateDiff(drinksDiffUtil)
        drinkList = listOfDrinksItems

        Log.d("drinkList", "$drinkList")
        diffUtilResult.dispatchUpdatesTo(this)
    }
}