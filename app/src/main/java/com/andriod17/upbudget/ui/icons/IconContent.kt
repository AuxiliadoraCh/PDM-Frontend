package com.andriod17.upbudget.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconContent: ImageVector
    get() {
        if (_iconContent != null) return _iconContent!!
        _iconContent = Builder(
            name = "IconContent", defaultWidth = 16.dp, defaultHeight = 20.dp,
            viewportWidth = 16f, viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF180F3E)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeLineWidth = 0.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(3.5f, 20f)
                curveTo(2.53f, 20f, 1.71f, 19.66f, 1.03f, 18.98f)
                curveTo(0.34f, 18.29f, 0f, 17.47f, 0f, 16.5f)
                lineTo(0f, 3.5f)
                curveTo(0f, 2.53f, 0.34f, 1.71f, 1.03f, 1.03f)
                curveTo(1.71f, 0.34f, 2.53f, 0f, 3.5f, 0f)
                lineTo(16f, 0f)
                lineTo(16f, 15f)
                curveTo(15.58f, 15f, 15.23f, 15.15f, 14.94f, 15.44f)
                curveTo(14.65f, 15.73f, 14.5f, 16.08f, 14.5f, 16.5f)
                curveTo(14.5f, 16.92f, 14.65f, 17.27f, 14.94f, 17.56f)
                curveTo(15.23f, 17.85f, 15.58f, 18f, 16f, 18f)
                lineTo(16f, 20f)
                lineTo(3.5f, 20f)
                close()

                moveTo(2f, 13.325f)
                curveTo(2.23f, 13.21f, 2.48f, 13.13f, 2.73f, 13.08f)
                curveTo(2.98f, 13.03f, 3.23f, 13f, 3.5f, 13f)
                lineTo(4f, 13f)
                lineTo(4f, 2f)
                lineTo(3.5f, 2f)
                curveTo(3.08f, 2f, 2.73f, 2.15f, 2.44f, 2.44f)
                curveTo(2.15f, 2.73f, 2f, 3.08f, 2f, 3.5f)
                lineTo(2f, 13.325f)
                close()

                moveTo(6f, 13f)
                lineTo(14f, 13f)
                lineTo(14f, 2f)
                lineTo(6f, 2f)
                lineTo(6f, 13f)
                close()

                moveTo(3.5f, 18f)
                lineTo(12.825f, 18f)
                curveTo(12.725f, 17.77f, 12.65f, 17.53f, 12.59f, 17.29f)
                curveTo(12.53f, 17.05f, 12.5f, 16.78f, 12.5f, 16.5f)
                curveTo(12.5f, 16.23f, 12.52f, 15.98f, 12.575f, 15.725f)
                curveTo(12.625f, 15.475f, 12.708f, 15.233f, 12.825f, 15f)
                lineTo(3.5f, 15f)
                curveTo(3.067f, 15f, 2.708f, 15.146f, 2.425f, 15.438f)
                curveTo(2.142f, 15.729f, 2f, 16.083f, 2f, 16.5f)
                curveTo(2f, 16.933f, 2.142f, 17.292f, 2.425f, 17.575f)
                curveTo(2.708f, 17.858f, 3.067f, 18f, 3.5f, 18f)
                close()
            }
        }.build()
        return _iconContent!!
    }

private var _iconContent: ImageVector? = null