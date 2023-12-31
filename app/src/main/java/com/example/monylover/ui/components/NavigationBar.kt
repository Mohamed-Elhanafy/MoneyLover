package com.example.monylover.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.monylover.R
import com.example.monylover.ui.theme.TopAppBarBackground

@Composable
fun NavBar(backStackEntry: NavBackStackEntry?, navController: NavController) {
    NavigationBar(
        containerColor = TopAppBarBackground
    ) {


        NavigationBarItem(

        selected = backStackEntry?.destination?.route == "expanses",
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
            selected = backStackEntry?.destination?.route == "reports",
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
            selected = backStackEntry?.destination?.route == "add",
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
            selected = backStackEntry?.destination?.route?.startsWith("setting") ?: false,
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



