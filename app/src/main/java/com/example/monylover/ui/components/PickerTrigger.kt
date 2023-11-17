package com.example.monylover.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monylover.R
import com.example.monylover.ui.theme.FillTertiary
import com.example.monylover.ui.theme.MonyLoverTheme
import com.example.monylover.ui.theme.Shapes
import com.example.monylover.ui.theme.Typography

@Composable
fun PickerTrigger(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = Shapes.medium,
        color = FillTertiary,
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(label, style = Typography.titleSmall)
            Icon(
                painterResource(R.drawable.ic_unfold_more),
                contentDescription = "Open picker",
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    MonyLoverTheme {
        PickerTrigger("this week", onClick = {})
    }
}