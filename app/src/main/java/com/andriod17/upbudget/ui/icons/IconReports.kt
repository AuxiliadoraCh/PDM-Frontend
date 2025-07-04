package com.andriod17.upbudget.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconReports: ImageVector
    get() {
        if (_iconReports != null) return _iconReports!!
        _iconReports = Builder(
            name = "IconReports", defaultWidth = 19.dp, defaultHeight = 20.dp,
            viewportWidth = 19f, viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF180F3E)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeLineWidth = 0.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(5f, 11.65f)
                lineTo(5f, 4f)
                lineTo(8f, 4f)
                lineTo(8f, 11.65f)
                lineTo(6.5f, 10.25f)
                lineTo(5f, 11.65f)
                close()

                moveTo(10f, 13.15f)
                lineTo(10f, 0f)
                lineTo(13f, 0f)
                lineTo(13f, 10.15f)
                lineTo(10f, 13.15f)
                close()

                moveTo(0f, 16.6f)
                lineTo(0f, 8f)
                lineTo(3f, 8f)
                lineTo(3f, 13.6f)
                lineTo(0f, 16.6f)
                close()

                moveTo(0f, 19.05f)
                lineTo(6.45f, 12.6f)
                lineTo(10f, 15.65f)
                lineTo(15.6f, 10.05f)
                lineTo(14f, 10.05f)
                lineTo(14f, 8.05f)
                lineTo(19f, 8.05f)
                lineTo(19f, 13.05f)
                lineTo(17f, 13.05f)
                lineTo(17f, 11.45f)
                lineTo(10.1f, 18.35f)
                lineTo(6.55f, 15.3f)
                lineTo(2.8f, 19.05f)
                lineTo(0f, 19.05f)
                close()
            }
        }.build()
        return _iconReports!!
    }

private var _iconReports: ImageVector? = null
