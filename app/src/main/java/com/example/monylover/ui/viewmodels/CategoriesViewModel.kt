package com.example.monylover.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.monylover.ui.theme.Primary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CategoriesState(
    val newCategoryName: String = "",
    val newCategoryColor: Color = Primary,
    val isDialogShow: Boolean = false
    )

class CategoriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState: StateFlow<CategoriesState> = _uiState.asStateFlow()


    fun onCategoryNameChange(newCategoryName: String) {
        _uiState.update { currentState ->
            currentState.copy(newCategoryName = newCategoryName)
        }
    }

    fun onCategoryColorChange(newCategoryColor: Color) {
        _uiState.update { currentState ->
            currentState.copy(newCategoryColor = newCategoryColor)
        }
    }
    
    fun onDialogShowChange(isDialogShow: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isDialogShow = isDialogShow)
        }
    }


}