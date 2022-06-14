package com.olajide.codelabllc.drinks.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olajide.codelabllc.R
import com.olajide.codelabllc.databinding.DrinksLayoutBinding
import com.olajide.codelabllc.drinks.data.entity.DrinksDetail
import com.olajide.codelabllc.drinks.presentation.adapter.DrinksAdapter
import com.olajide.codelabllc.drinks.presentation.viewmodel.DrinksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinksActivity: AppCompatActivity(R.layout.drinks_layout) {
    private lateinit var binding: DrinksLayoutBinding
    private val viewModel: DrinksViewModel by viewModels()

    private var drinkUpdateList = ArrayList<DrinksDetail>()
    private lateinit var drinksAdapter: DrinksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drinksAdapter = DrinksAdapter()
        setUpRecyclerView()
        viewModel.fetchDrinks("a").toString()

    }

    private fun setUpRecyclerView() {
        binding.rvDrinks.apply {
            adapter = drinksAdapter
            layoutManager =
                LinearLayoutManager(context!!,  LinearLayoutManager.VERTICAL, false)
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }
    }
}