package com.example.monylover.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monylover.ui.components.MyDatePickerDialog
import com.example.monylover.ui.components.TableRow
import com.example.monylover.ui.components.UnstyledTextField
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.MonyLoverTheme
import com.example.monylover.ui.theme.Primary
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.TopAppBarBackground
import java.time.Instant
import java.time.ZoneOffset
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, name: String) {
    val recurrenceOptions = listOf("None", "Daily", "Weekly", "Monthly", "Yearly")
    val categoriesOptions = listOf("groceries", "transportation", "entertainment", "bills", "other")
    var selectedRecurrence by remember { mutableStateOf(recurrenceOptions[0]) }
    var selectedCategory by remember { mutableStateOf(recurrenceOptions[0]) }

    var selectedDate by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Add") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground,
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(Shapes.large)
                        .fillMaxWidth()
                        .background(BackgroundElevated)

                ) {
                    TableRow(label = "Amount", detailContent = {
                        UnstyledTextField(
                            value = "Amount",
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Amount") },
                            arrangement = Arrangement.End,
                            maxLines = 1,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            )

                        )
                    })
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Recurrence") {
                        var recurrenceMenuExpanded by remember { mutableStateOf(false) }
                        TextButton(
                            onClick = { recurrenceMenuExpanded = true },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.height(16.dp)
                        ) {
                            Text(text = selectedRecurrence)
                            DropdownMenu(
                                expanded = recurrenceMenuExpanded,
                                onDismissRequest = { recurrenceMenuExpanded = false }) {
                                recurrenceOptions.forEach { label ->
                                    DropdownMenuItem(
                                        text = { Text(text = label) },
                                        onClick = {
                                            selectedRecurrence = label
                                            recurrenceMenuExpanded = false
                                        })
                                }
                            }
                        }

                    }
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))

                    val showDialog = rememberSaveable { mutableStateOf(false) }

                    TableRow(label = "Date", onClick = {
                        showDialog.value = true

                    }){
                        if (showDialog.value){
                            MyDatePickerDialog(
                                onDateSelected = {
                                    selectedDate = it
                                    showDialog.value = false
                                },
                                onDismiss = { showDialog.value = false }
                            )
                        }

                        Text(text = selectedDate , fontSize = 14.sp, modifier = Modifier.padding(end = 10.dp))
                    }

                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Note", detailContent = {
                        UnstyledTextField(
                            value = "",
                            placeholder = { Text("Leave some notes") },
                            arrangement = Arrangement.End,
                            onValueChange = { },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                        )
                    })
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Category", detailContent = {
                        var categoriesMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        TextButton(
                            onClick = { categoriesMenuOpened = true },
                            shape = Shapes.large,
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.height(20.dp)
                        ) {
                            Text(
                                selectedCategory,
                            )
                            DropdownMenu(expanded = categoriesMenuOpened,
                                onDismissRequest = { categoriesMenuOpened = false }) {
                                categoriesOptions?.forEach { category ->
                                    DropdownMenuItem(text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Surface(
                                                modifier = Modifier.size(10.dp),
                                                shape = CircleShape,
                                                color = Primary
                                            ) {}
                                            Text(
                                                category, modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }, onClick = {
                                        selectedCategory = category
                                        categoriesMenuOpened = false
                                    })
                                }
                            }
                        }
                    })
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(16.dp),
                    shape = Shapes.medium
                ) {
                    Text(text = "Submit expense")
                }
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddPreview() {
    MonyLoverTheme {
        AddScreen(navController = rememberNavController(), name = "Add")
    }
}