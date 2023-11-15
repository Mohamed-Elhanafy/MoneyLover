package com.example.monylover.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monylover.Greeting
import com.example.monylover.ui.components.TableRow
import com.example.monylover.ui.components.UnstyledTextField
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.MonyLoverTheme
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, name: String) {
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
                            Text(text = "None")
                            DropdownMenu(
                                expanded = recurrenceMenuExpanded,
                                onDismissRequest = { recurrenceMenuExpanded = false }) {
                                DropdownMenuItem(
                                    text = { Text(text = "None") },
                                    onClick = { /*TODO*/ })
                                DropdownMenuItem(
                                    text = { Text(text = "Daily") },
                                    onClick = { /*TODO*/ })
                                DropdownMenuItem(
                                    text = { Text(text = "Weekly") },
                                    onClick = { /*TODO*/ })
                                DropdownMenuItem(
                                    text = { Text(text = "Monthly") },
                                    onClick = { /*TODO*/ })
                                DropdownMenuItem(
                                    text = { Text(text = "Yearly") },
                                    onClick = { /*TODO*/ })
                            }
                        }

                    }
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Date")
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
                    TableRow(label = "Category")
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


@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddPreview() {
    MonyLoverTheme {
        AddScreen(navController = rememberNavController(), name = "Add")
    }
}