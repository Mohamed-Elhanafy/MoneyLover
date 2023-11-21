package com.example.monylover.ui.components.expenses

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.monylover.models.Category
import com.example.monylover.models.Expense
import com.example.monylover.models.Recurrence
import com.example.monylover.models.groupedByDay
import com.example.monylover.ui.theme.MonyLoverTheme
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

val list = listOf(
    Expense(
        amount = 100.0,
        note = "pizza",
        date = LocalDateTime.now().minus(
            Random.nextInt( 300,  345600).toLong(),
            ChronoUnit.SECONDS
        ),
        recurrence = Recurrence.None,
        category =  Category(
            name = "Food",
            color = Color(
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            ).toArgb()
        ) )
    ,
    Expense(
        amount = 100.0,
        note = "Coctail",
        date = LocalDateTime.now(),
        recurrence = Recurrence.None,
        category =  Category(
            name = "Bills",
            color = Color(
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            ).toArgb()
        ) ),
    Expense(
        amount = 104.0,
        note = "car wash",
        date = LocalDateTime.now(),
        recurrence = Recurrence.None,
        category =  Category(
            name = "car",
            color = Color(
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            ).toArgb()
        ) )

)
@Composable
fun ExpensesList(expenses: List<Expense>, modifier: Modifier = Modifier) {
    val groupedExpenses = expenses.groupedByDay()

    Column(modifier = modifier) {
        if (groupedExpenses.isEmpty()) {
            Text("No data for selected date range.", modifier = Modifier.padding(top = 32.dp))
        } else {
            groupedExpenses.keys.forEach { date ->
                if (groupedExpenses[date] != null) {
                    ExpensesDayGroup(
                        date = date,
                        dayExpenses = groupedExpenses[date]!!,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExpensesListPreview() {
    MonyLoverTheme {
        ExpensesList(list)
    }
}