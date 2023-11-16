package com.example.monylover.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.SystemGray
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorPickerDialog(
    setShowDialog: (Boolean) -> Unit,
    setValue: (Color) -> Unit,

    ) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        val controller = rememberColorPickerController()
        val theNewColor = remember { mutableStateOf(Color.White) }
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .clip(Shapes.large)
                    .background(SystemGray)

            ) {
                Column {
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(340.dp)
                            .padding(10.dp)
                        ,
                        controller = controller,
                        onColorChanged = { colorEnvelope: ColorEnvelope ->

                            val color: Color = colorEnvelope.color // ARGB color value.
                            val hexCode: String =
                                colorEnvelope.hexCode // Color hex code, which represents color value.
                            val fromUser: Boolean =
                                colorEnvelope.fromUser // Represents this event is triggered by user or not.

                           // controller.setWheelColor(color)

                            theNewColor.value = color
                        }
                    )
                    Surface(
                        shape = CircleShape,
                        color = theNewColor.value,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    ) {

                    }
                    BrightnessSlider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(35.dp),
                        controller = controller,
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        TextButton(
                            onClick = {
                                setShowDialog(false)
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .requiredWidth(100.dp)
                                .heightIn(40.dp)
                        ) {
                            Text(text = "Cancel")
                        }

                        TextButton(
                            onClick = {
                                setValue(theNewColor.value)
                                setShowDialog(false)

                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .requiredWidth(100.dp)
                                .heightIn(40.dp)
                        ) {
                            Text(text = "OK")
                        }


                    }
                }
            }
        }


    }
}
