package com.example.monylover.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.monylover.Greeting

@Composable
fun AddScreen(navController: NavController, name:String) {
    Greeting(name = "add")
}