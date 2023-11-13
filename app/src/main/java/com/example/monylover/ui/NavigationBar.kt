package com.example.monylover.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.monylover.R

@Composable
fun NavBar(backStackEntry: State<NavBackStackEntry?>, navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = backStackEntry.value?.destination?.route == "expanses",
            onClick = { navController.navigate("expanses") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.upload_ic),
                    contentDescription = "Expanses icon"
                )
            },
            label = {
                Text(text = "Expanses")
            }
        )
        NavigationBarItem(
            selected = backStackEntry.value?.destination?.route == "reports",
            onClick = { navController.navigate("reports") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.bar_chart_ic),
                    contentDescription = "Reports icon"
                )
            },
            label = {
                Text(text = "Reports")
            }
        )
        NavigationBarItem(
            selected = backStackEntry.value?.destination?.route == "add",
            onClick = { navController.navigate("add") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.add_ic),
                    contentDescription = "Add"
                )
            },
            label = {
                Text(text = "Add")
            }
        )
        NavigationBarItem(
            selected = backStackEntry.value?.destination?.route == "setting",
            onClick = { navController.navigate("setting") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.setting_ic),
                    contentDescription = "setting icon"
                )
            },
            label = {
                Text(text = "setting")
            }
        )
    }
}



