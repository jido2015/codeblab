package com.olajide.codelabllc.drinks.presentation.activity

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

        //to call a method whenever there is some change on the EditText

        binding.searchView.queryHint = "Search for drinks by alphabet …"
        binding.searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.fetchDrinks(query).toString()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.fetchDrinks(newText).toString()
                return true
            }
        })
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