package com.andriod17.upbudget.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconHome: ImageVector
    get() {
        if (_iconHome != null) return _iconHome!!
        _iconHome = Builder(
            name = "IconHome", defaultWidth = 23.dp, defaultHeight = 17.dp,
            viewportWidth = 23f, viewportHeight = 17f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF180F3E)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeLineWidth = 0.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8f, 17f)
                lineTo(8f, 11f)
                lineTo(14f, 11f)
                lineTo(14f, 17f)
                lineTo(8f, 17f)
                close()
                moveTo(10f, 15f)
                lineTo(12f, 15f)
                lineTo(12f, 13f)
                lineTo(10f, 13f)
                lineTo(10f, 15f)
                close()

                moveTo(1.2f, 10f)
                lineTo(0f, 8.4f)
                lineTo(11f, 0f)
                lineTo(15f, 3.05f)
                lineTo(15f, 1f)
                lineTo(18f, 1f)
                lineTo(18f, 5.35f)
                lineTo(22f, 8.4f)
                lineTo(20.8f, 10f)
                lineTo(11f, 2.525f)
                lineTo(1.2f, 10f)
                close()
            }
        }.build()
        return _iconHome!!
    }

private var _iconHome: ImageVector? = null
