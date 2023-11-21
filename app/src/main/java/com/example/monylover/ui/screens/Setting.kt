package com.example.monylover.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.monylover.Greeting
import com.example.monylover.data.db.RoomDb
import com.example.monylover.ui.components.TableRow
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.TopAppBarBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController , database: RoomDb , viewmodel: SettingViewModel = SettingViewModel()) {

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Setting") },
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
                    TableRow(label = "categories", hasArrow = true, onClick = {
                        run {
                            navController.navigate("setting/categories")

                        }
                    })
                    Divider(Modifier.padding(start = 16.dp, end = 16.dp))
                    TableRow(label = "erase all data", isDestructive = true , onClick = {
                        viewmodel.deleteAllData(database)
                    })
                }
            }
        }
    )
}