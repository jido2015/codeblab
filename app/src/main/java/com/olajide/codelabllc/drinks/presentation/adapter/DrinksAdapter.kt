package com.olajide.codelabllc.drinks.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.olajide.codelabllc.databinding.DrinksItemBinding
import com.olajide.codelabllc.drinks.common.DrinksDiffUtils
import com.olajide.codelabllc.drinks.data.entity.Drink
import com.olajide.codelabllc.drinks.data.entity.DrinksDetail

class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>(), Filterable {
    private var drinkList = ArrayList<Drink>()

    // stores and recycles views as they are scrolled off screen
    class DrinksViewHolder(private val binding: DrinksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind( drinksDetail: Drink) {
            binding.strCategory.text = drinksDetail.strCategory
            binding.strImageSource.load(drinksDetail.strImageSource)
            binding.strIngredient1.text = drinksDetail.strIngredient1
            binding.strIngredient2.text = drinksDetail.strIngredient2
            binding.strIngredient3.text = drinksDetail.strIngredient3
            binding.strDrink.text = drinksDetail.strDrink
            binding.strInstructions.text = drinksDetail.strInstructions
            binding.strAlcoholic.text = drinksDetail.strAlcoholic
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


    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


    fun setItem(listOfDrinksItems: ArrayList<Drink>) {
        val drinksDiffUtil =
            DrinksDiffUtils(drinkList, listOfDrinksItems)
        val diffUtilResult = DiffUtil.calculateDiff(drinksDiffUtil)
        drinkList = listOfDrinksItems

        Log.d("drinkList", "$drinkList")
        diffUtilResult.dispatchUpdatesTo(this)
    }
}