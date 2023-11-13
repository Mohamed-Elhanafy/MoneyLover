package com.example.monylover.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.monylover.Greeting
import com.example.monylover.ui.theme.TopAppBarBackground

@ExperimentalMaterial3Api
@Composable
fun ExpanseScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text(text = "Expanse") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                ))
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Expanse Screen")
            }
        }
    )
}