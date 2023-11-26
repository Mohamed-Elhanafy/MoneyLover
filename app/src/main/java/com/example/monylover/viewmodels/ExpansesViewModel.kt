package com.example.monylover.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monylover.data.db.RoomDb
import com.example.monylover.models.Expense
import com.example.monylover.models.Recurrence

import com.example.monylover.ui.utils.calculateDateRange

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExpansesState(
    val recurrence: Recurrence = Recurrence.None,

    val sumTotal: Double = 0.0,
    val expanses: List<Expense> = listOf()

)

class ExpansesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExpansesState())
    val uiState : StateFlow<ExpansesState> = _uiState.asStateFlow()



    fun getExpanses(database: RoomDb){
        viewModelScope.launch(Dispatchers.IO){
            _uiState.update { currentState->
                currentState.copy(
                    expenses = database.databaseDao().getAllExpenses()
                )
            }
        }
    }

    fun updateSumTotal(double: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    sumTotal = double
                )
            }
        }
    }


    fun setRecurrence(recurrence: Recurrence , database: RoomDb) {
        viewModelScope.launch(Dispatchers.IO) {
            val (start, end) = calculateDateRange(recurrence, 0)

            val filteredExpenses = database.databaseDao().getAllExpenses().filter { expense ->
                (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                    .isBefore(end)) || expense.date.toLocalDate()
                    .isEqual(start) || expense.date.toLocalDate().isEqual(end)
            }

            val sumTotal = filteredExpenses.sumOf { it.amount }

            _uiState.update { currentState ->
                currentState.copy(
                    recurrence = recurrence,
                    expenses = filteredExpenses,
                    sumTotal = sumTotal
                )
            }
        }
    }

  /*  fun setRecurrence(recurrence: Recurrence) {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update { currentState ->
                currentState.copy(
                    recurrence = recurrence,
                    expenses = filteredExpenses,
                    sumTotal = sumTotal
                )
            }
        }
    }*/
}