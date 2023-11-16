package com.example.monylover.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.monylover.ui.components.UnstyledTextField
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.Primary
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.TopAppBarBackground
import com.example.monylover.ui.viewmodels.CategoriesViewModel
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.Locale.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(
    navController: NavController,
    viewmodel: CategoriesViewModel = CategoriesViewModel()
) {
    val uiState by viewmodel.uiState.collectAsState()

    val categories = listOf(
        "A",
        "B",
        "C",
        "D"

    )
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


                Column(Modifier.weight(1f)) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(Shapes.large)
                            .fillMaxWidth()
                            .background(BackgroundElevated)
                    ) {
                        items(categories) { category ->


                            TableRow( onClick = {}) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
                                ) {
                                    Surface(
                                        color = Primary,
                                        shape = CircleShape,
                                        border = BorderStroke(
                                            width = 1.dp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.size(16.dp)
                                    ) {}
                                    Text(
                                        category,
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 10.dp
                                        ),
                                    )
                                }

                            }
                            HorizontalDivider(Modifier.padding(start = 16.dp, end = 16.dp))
                        }
                    }
                }


                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (uiState.isDialogShow) {
                        ColorPickerDialog(setShowDialog = {
                            viewmodel.onDialogShowChange(it)
                        }) {
                            //parce color from string to color
                            viewmodel.onCategoryColorChange(it)
                            Log.i("HomePage", "HomePage : $it")
                        }
                    }

                    Surface(
                        shape = CircleShape,
                        color = uiState.newCategoryColor,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(24.dp)
                            .clickable {

                                viewmodel.onDialogShowChange(true)
                            },
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        )
                    ) {}

                    Surface(
                        color = BackgroundElevated,
                        modifier = Modifier
                            .height(44.dp)
                            .weight(1f)
                            .padding(start = 16.dp),
                        shape = Shapes.large,

                        ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            UnstyledTextField(
                                value = uiState.newCategoryName,
                                onValueChange = viewmodel::onCategoryNameChange,
                                placeholder = { Text("Category name") },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                maxLines = 1,
                            )
                        }
                    }

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.Send,
                            "Create category"
                        )
                    }


                }
            }


        }
    )

}


@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategories() {
    Categories(navController = rememberNavController())
}