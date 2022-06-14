package com.olajide.codelabllc.drinks.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.olajide.codelabllc.databinding.DrinksLayoutBinding
import com.olajide.codelabllc.drinks.data.entity.Drink
import com.olajide.codelabllc.drinks.data.entity.DrinksDto
import com.olajide.codelabllc.drinks.presentation.adapter.DrinksAdapter
import com.olajide.codelabllc.drinks.presentation.viewmodel.DrinksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinksActivity: AppCompatActivity() {
    private lateinit var binding: DrinksLayoutBinding
    private val viewModel: DrinksViewModel by viewModels()

    private var drinkUpdateList = ArrayList<Drink>()
    private lateinit var drinksAdapter: DrinksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrinksLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        drinksAdapter = DrinksAdapter()
        setUpRecyclerView()

        searchResponse()
        viewModel.fetchDrinks("a").toString()
    }

    private fun setUpRecyclerView() {
        binding.rvDrinks.apply {
            adapter = drinksAdapter
            layoutManager = LinearLayoutManager(context!!)
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }
    }


    private fun searchResponse(){
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect{
                when (it){
                    is DrinksViewModel.DrinksEvent.Success -> {
                        try {
                            val drinksList: DrinksDto? = it.result
                            drinkUpdateList = drinksList!!.drinks
                            drinksAdapter.setItem(drinkUpdateList)
                            drinksAdapter.notifyDataSetChanged()

                            Log.e("DrinksActivity", "log Array $drinkUpdateList")

                        } catch (t: Throwable) {
                            Log.e("DrinksActivity", "Could not parse malformed JSON")
                        }
                    }
                    is DrinksViewModel.DrinksEvent.Failure -> {
                    }
                    is DrinksViewModel.DrinksEvent.Loading -> {
                        Log.e("DrinksActivity", "Waiting for connection response")

                    }else -> Unit
                }
            }
        }

    }
}