package com.example.monylover.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.monylover.R
import com.example.monylover.ui.components.ColorPickerDialog
import com.example.monylover.ui.components.TableRow
import com.example.monylover.ui.theme.TopAppBarBackground
import com.example.monylover.ui.viewmodels.CategoriesViewModel
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(
    navController: NavController,
    viewmodel: CategoriesViewModel = CategoriesViewModel()
) {

    val uiState by viewmodel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text(text = "categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                ),
                navigationIcon = {

                    Surface(onClick = { navController.popBackStack() }) {
                        Row {
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "setting icon",
                            )
                            Text(text = "setting")
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TableRow(label = "A")
                HorizontalDivider(Modifier.padding(start = 16.dp, end = 16.dp))
                TableRow(label = "B")
                HorizontalDivider(Modifier.padding(start = 16.dp, end = 16.dp))
                TableRow(label = "C")
                HorizontalDivider(Modifier.padding(start = 16.dp, end = 16.dp))


                if (uiState.isDialogShow) {
                    ColorPickerDialog(setShowDialog = {
                        viewmodel.onDialogShowChange(it)
                    }) {
                        Log.i("HomePage", "HomePage : $it")
                    }
                }
                TableRow(label = "D", onClick = {
                    viewmodel.onDialogShowChange(true)
                })

                //  Spacer(modifier = Modifier.weight(1f))


            }


        }
    )

}


@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategories() {
    Categories(navController = rememberNavController())
}