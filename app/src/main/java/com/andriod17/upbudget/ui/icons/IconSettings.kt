package com.andriod17.upbudget.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconSettings: ImageVector
    get() {
        if (_iconSettings != null) return _iconSettings!!
        _iconSettings = Builder(
            name = "IconSettings", defaultWidth = 22.dp, defaultHeight = 22.dp,
            viewportWidth = 22f, viewportHeight = 22f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF180F3E)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeLineWidth = 0.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 15.5f)
                arcToRelative(3.5f, 3.5f, 0.0f, true, true, 0f, -7f)
                arcToRelative(3.5f, 3.5f, 0.0f, false, true, 0f, 7f)
                close()
                moveTo(19.43f, 12.98f)
                curveToRelative(0.04f, -0.32f, 0.07f, -0.65f, 0.07f, -0.98f)
                reflectiveCurveToRelative(-0.03f, -0.66f, -0.07f, -0.98f)
                lineToRelative(2.11f, -1.65f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.12f, -0.64f)
                lineToRelative(-2f, -3.46f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.61f, -0.22f)
                lineToRelative(-2.49f, 1f)
                arcToRelative(7.03f, 7.03f, 0.0f, false, false, -1.7f, -0.98f)
                lineToRelative(-0.38f, -2.65f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 14f, 2f)
                horizontalLineToRelative(-4f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.49f, 0.42f)
                lineToRelative(-0.38f, 2.65f)
                curveToRelative(-0.63f, 0.26f, -1.21f, 0.6f, -1.7f, 0.98f)
                lineToRelative(-2.49f, -1f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.61f, 0.22f)
                lineToRelative(-2f, 3.46f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.12f, 0.64f)
                lineToRelative(2.11f, 1.65f)
                curveToRelative(-0.04f, 0.32f, -0.07f, 0.65f, -0.07f, 0.98f)
                reflectiveCurveToRelative(0.03f, 0.66f, 0.07f, 0.98f)
                lineTo(2.57f, 14.63f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.12f, 0.64f)
                lineToRelative(2f, 3.46f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.61f, 0.22f)
                lineToRelative(2.49f, -1f)
                arcToRelative(7.03f, 7.03f, 0.0f, false, false, 1.7f, 0.98f)
                lineToRelative(0.38f, 2.65f)
                arcTo(0.5f, 0.5f, 0.0f, false, false, 10f, 22f)
                horizontalLineToRelative(4f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.49f, -0.42f)
                lineToRelative(0.38f, -2.65f)
                curveToRelative(0.63f, -0.26f, 1.21f, -0.6f, 1.7f, -0.98f)
                lineToRelative(2.49f, 1f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, 0.61f, -0.22f)
                lineToRelative(2f, -3.46f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, false, -0.12f, -0.64f)
                lineTo(19.43f, 12.98f)
                close()
            }
        }.build()
        return _iconSettings!!
    }

private var _iconSettings: ImageVector? = null
