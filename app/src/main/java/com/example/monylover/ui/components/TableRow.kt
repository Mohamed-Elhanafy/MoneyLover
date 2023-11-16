package com.example.monylover.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.monylover.R
import com.example.monylover.ui.theme.BackgroundElevated
import com.example.monylover.ui.theme.Destructive
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.TextPrimary
import com.example.monylover.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TableRow(
    label: String? = null,
    modifier: Modifier = Modifier,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    onClick: () -> Unit = {},
    detailContent: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null
) {

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        if (label != null) {
            Text(
                text = label,
                style = Typography.bodyMedium,
                color = if (isDestructive == true) Destructive else TextPrimary
            )
        }


        Spacer(modifier = Modifier.weight(1f))
        if (content != null) {
            content()
        }
        if (hasArrow) {
            Icon(
                painter = painterResource(id = R.drawable.round_chevron_right_24),
                contentDescription = "arrow icon",
            )
        }

        if (detailContent != null) {
            detailContent()
        }
    }

}