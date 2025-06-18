package com.andriod17.upbudget.data.model.settings

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsOption(
    val icon: ImageVector,
    val title: String,
    val onClick: () -> Unit
)
