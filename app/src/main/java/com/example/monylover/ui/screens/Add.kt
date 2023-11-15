package com.example.monylover.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.monylover.Greeting
import com.example.monylover.ui.components.TableRow
import com.example.monylover.ui.components.UnstyledTextField
import com.example.monylover.ui.theme.BackgroundElevated
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
            Column(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(Shapes.large)
                        .fillMaxWidth()
                        .background(BackgroundElevated)

                ) {
                    TableRow(label = "Amount", detailContent = {
                        UnstyledTextField(
                            value = "",
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
                    TableRow(label = "Recurrence")
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Date")
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Note", detailContent = {
                        UnstyledTextField(
                            value = "",
                            placeholder = { Text("Leave some notes") },
                            arrangement = Arrangement.End,
                            onValueChange = {  },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                        )
                    })
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "Category")
                }
            }
        }
    )
}