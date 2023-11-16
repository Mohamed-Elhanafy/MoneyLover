package com.example.monylover.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.monylover.ui.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddScreenState(
    val amount: String = "0",
    val recurrence: Recurrence = Recurrence.None,
    val date: String? = null,
    val note: String = "",
    val category: String? = null, // TODO: Change to Category when build category model
)

class AddViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(AddScreenState())
    val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

    fun setAmount(amount: String) {
        var parsed = amount.toDoubleOrNull()

        if (amount.isEmpty()) {
            parsed = 0.0
        }

        if (parsed != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    amount = amount.trim().ifEmpty { "0" },
                )
            }
        }
    }

    fun setRecurrence(recurrence: Recurrence) {
        _uiState.update { currentState ->
            currentState.copy(
                recurrence = recurrence,
            )
        }
    }

    fun setDate(date: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = date,
            )
        }
    }

    fun setNote(note: String) {
        _uiState.update { currentState ->
            currentState.copy(
                note = note,
            )
        }
    }

    fun setCategory(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category,
            )
        }
    }



    fun submitExpense() {
        //todo save to local Db
    }
}