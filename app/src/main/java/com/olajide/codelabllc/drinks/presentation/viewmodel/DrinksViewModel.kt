package com.olajide.codelabllc.drinks.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olajide.codelabllc.drinks.data.entity.DrinksDto
import com.olajide.codelabllc.drinks.domain.repository.DrinksRepository
import com.olajide.codelabllc.drinks.resource.DataHandler
import com.olajide.codelabllc.drinks.resource.DispatchProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val mainRepository: DrinksRepository,
    private val dispatchers: DispatchProvider
) : ViewModel() {
    sealed class DrinksEvent {
        class Success(val result: DrinksDto?) : DrinksEvent()
        class Failure(val errorText: String) : DrinksEvent()
        object Loading : DrinksEvent()
        object Empty : DrinksEvent()
    }

    //StateFow
    private val _conversion = MutableStateFlow<DrinksEvent>(DrinksEvent.Empty)
    val conversion: StateFlow<DrinksEvent> = _conversion

    fun fetchDrinks(letter:String) {
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = DrinksEvent.Loading
            when (val responseData = mainRepository.getDrinks(letter)) {
                is DataHandler.Failure -> {
                    _conversion.value = DrinksEvent.Failure(responseData.message!!)
                }

                is DataHandler.Success -> {
                    Log.d("LogListOfDrinks", responseData.data!!.drinks.toString())
                    _conversion.value = DrinksEvent.Success(responseData.data)
                    Log.d("LogListOfDrinks", "Success")
                }
                else -> {}
            }
        }
    }
}