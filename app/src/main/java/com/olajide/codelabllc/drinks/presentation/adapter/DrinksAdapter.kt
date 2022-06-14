package com.olajide.codelabllc.drinks.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.olajide.codelabllc.databinding.DrinksItemBinding
import com.olajide.codelabllc.drinks.common.DrinksDiffUtils
import com.olajide.codelabllc.drinks.data.entity.DrinksDetail

class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.ViewHolder>(), Filterable {
    private var drinkList = ArrayList<DrinksDetail>()

    // stores and recycles views as they are scrolled off screen
    class ViewHolder(val binding: DrinksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind( drinksDetail: DrinksDetail) {
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
    ): ViewHolder {

        return ViewHolder(
            DrinksItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentItem = drinkList[position]
        holder.bind(currentItem)
    }


    override fun getItemCount(): Int = drinkList.size


    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


    fun setItem(listOfBillsItems: ArrayList<DrinksDetail>) {
        val payBillsDiffUtil =
            DrinksDiffUtils(drinkList, listOfBillsItems)
        val diffUtilResult = DiffUtil.calculateDiff(payBillsDiffUtil)
        drinkList = listOfBillsItems
        diffUtilResult.dispatchUpdatesTo(this)
    }
}