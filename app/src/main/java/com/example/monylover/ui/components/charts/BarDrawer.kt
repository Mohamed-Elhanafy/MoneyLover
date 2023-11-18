package com.example.monylover.ui.components.charts


import com.example.monylover.models.Recurrence

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.monylover.ui.theme.SystemGray
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.BarDrawer


class MyBarDrawer constructor(recurrence: Recurrence) :
    BarDrawer {
    private val barPaint = Paint().apply {
        this.isAntiAlias = true

    }


    private val rightOffset = when(recurrence) {
        Recurrence.Weekly -> 24f
        Recurrence.Monthly -> 6f
        Recurrence.Yearly -> 18f
        else -> 0f
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    ) {
        canvas.drawRoundRect(
            barArea.left,
            0f,
            barArea.right + rightOffset,
            barArea.bottom,
            16f,
            16f,
            barPaint.apply {
                color = SystemGray
            },
        )
        canvas.drawRoundRect(
            barArea.left,
            barArea.top,
            barArea.right + rightOffset,
            barArea.bottom,
            16f,
            16f,
            barPaint.apply {
                color = bar.color
            },
        )
    }
}