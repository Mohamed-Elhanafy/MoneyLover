package com.example.monylover.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monylover.data.db.RoomDb

import com.example.monylover.ui.components.PickerTrigger
import com.example.monylover.models.Recurrence
import com.example.monylover.ui.components.expenses.ExpensesList
import com.example.monylover.ui.theme.LabelSecondary
import com.example.monylover.ui.theme.MonyLoverTheme
import com.example.monylover.ui.theme.TopAppBarBackground
import com.example.monylover.ui.theme.Typography
import com.example.monylover.ui.utils.calculateDateRange
import com.example.monylover.viewmodels.ExpansesViewModel
import java.text.DecimalFormat

@ExperimentalMaterial3Api
@Composable
fun ExpanseScreen(
    navController: NavController,

    expanseViewModel: ExpansesViewModel = ExpansesViewModel(),

    database: RoomDb
) {

    val state by viewModel.uiState.collectAsState()
    var recurrenceMenuExpanded by remember { mutableStateOf(false) }

    expanseViewModel.getExpanses(database)




    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Expanse") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Total for :", style = Typography.bodyMedium)

                    val recurrence = listOf(
                        Recurrence.None,
                        Recurrence.Daily,
                        Recurrence.Weekly,
                        Recurrence.Monthly,
                        Recurrence.Yearly
                    )
                    Box {
                        PickerTrigger(
                            label = state.recurrence.target,
                            onClick = { recurrenceMenuExpanded = true },
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        DropdownMenu(
                            expanded = recurrenceMenuExpanded,
                            onDismissRequest = { recurrenceMenuExpanded = false }) {
                            recurrence.forEach { label ->
                                DropdownMenuItem(
                                    text = { Text(text = label.target) },
                                    onClick = {
                                        expanseViewModel.setRecurrence(label , database)


                                        recurrenceMenuExpanded = false
                                    })
                            }
                        }
                    }


                }

                Row(modifier = Modifier.padding(vertical = 32.dp)) {
                    Text(
                        "$",
                        style = Typography.bodyMedium,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp, top = 4.dp)
                    )
                    Text(
                        DecimalFormat("0.#").format(state.sumTotal),
                        style = Typography.titleLarge
                    )
                }





                ExpensesList(
                    state.expenses, modifier = Modifier

                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                )


            }
        }
    )
}


