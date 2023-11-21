package com.example.monylover.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monylover.data.db.RoomDb
import com.example.monylover.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoriesState(
    val newCategoryName: String = "",
    val newCategoryColor: Color = Color.White,
    val isDialogShow: Boolean = false,
    val categories: List<Category> = listOf()
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


    fun onAddCategoryClick(database: RoomDb) {
        viewModelScope.launch(Dispatchers.IO) {
            database.databaseDao().insertCategory(
                Category(
                    name = uiState.value.newCategoryName,
                    color = uiState.value.newCategoryColor.toArgb()
                )
            )

            _uiState.update { currentState ->
                currentState.copy(
                    categories = database.databaseDao().getAllCategories()
                )
            }
        }


    }


}