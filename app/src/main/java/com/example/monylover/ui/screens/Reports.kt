package com.example.monylover.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monylover.R
import com.example.monylover.models.Category
import com.example.monylover.models.Expense
import com.example.monylover.models.Recurrence
import com.example.monylover.ui.components.charts.MonthlyChart
import com.example.monylover.ui.components.charts.WeeklyChart
import com.example.monylover.ui.components.charts.YearlyChart
import com.example.monylover.ui.components.expenses.ExpensesList
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.LabelSecondary
import com.example.monylover.ui.theme.MonyLoverTheme
import com.example.monylover.ui.theme.TopAppBarBackground
import com.example.monylover.ui.theme.Typography
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(navController: NavController) {

    val recurrence: Recurrence = Recurrence.Weekly
    var menuOpened = remember { mutableStateOf(false) }
    var recurrenceSelected = remember { mutableStateOf(recurrence) }

    val recurrences = listOf(
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )


    val list = listOf(
        Expense(
            amount = 100.0,
            note = "pizza",
            date = LocalDateTime.now().minus(
                Random.nextInt(300, 345600).toLong(),
                ChronoUnit.SECONDS
            ),
            recurrence = Recurrence.None,
            category = Category(
                name = "Food",
                Color(
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255)
                )
            )
        ),
        Expense(
            amount = 100.0,
            note = "Coctail",
            date = LocalDateTime.now(),
            recurrence = Recurrence.None,
            category = Category(
                name = "Bills",
                Color(
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255)
                )
            )
        ),
        Expense(
            amount = 104.0,
            note = "car wash",
            date = LocalDateTime.now(),
            recurrence = Recurrence.None,
            category = Category(
                name = "car",
                Color(
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255),
                    Random.nextInt(0, 255)
                )
            )
        )

    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Reports") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground,
                ),
                actions = {
                    IconButton(onClick = { menuOpened.value = true }) {
                        Icon(
                            painterResource(id = R.drawable.ic_today),
                            contentDescription = "Change recurrence"
                        )
                    }
                    DropdownMenu(
                        expanded = menuOpened.value,
                        onDismissRequest = { menuOpened.value = false },
                    ) {
                        recurrences.forEach { recurrence ->
                            DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                                recurrenceSelected.value = recurrence
                                menuOpened.value = false
                            })
                        }
                    }
                }


            )

        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("12 Sep - 15 Sep", style = Typography.titleSmall)
                        Spacer(modifier = Modifier.padding(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("USD", style = Typography.bodyMedium, color = LabelSecondary)
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text("200", style = Typography.headlineMedium)
                        }

                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Avg/Day", style = Typography.titleSmall)
                        Spacer(modifier = Modifier.padding(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("USD", style = Typography.bodyMedium, color = LabelSecondary)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text("200", style = Typography.headlineMedium)
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    when (recurrenceSelected.value) {
                        Recurrence.Weekly -> {
                            WeeklyChart(expenses = list)
                        }

                        Recurrence.Monthly -> {
                            MonthlyChart(
                                expenses = list,
                                LocalDate.now()
                            )
                        }

                        Recurrence.Yearly -> {
                            YearlyChart(expenses = list)
                        }

                        else -> {}
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))

                ExpensesList(
                    expenses = list, modifier = Modifier
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                )
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReportsScreenPreview() {
    MonyLoverTheme {
        ReportsScreen(navController = rememberNavController())
    }
}