package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    title: String,
    onBackPressed: () -> Unit,
    onSettingsPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
    modifier = modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Color(0xFFDED8F6))
    ) {
    IconButton(
        onClick = onBackPressed,
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back",
            tint = Color(0xFF180F3E)
            )
    }
    Text(
        text = title,
        fontSize = 20.sp,
        color = Color(0xFF180F3E),
        modifier = Modifier.align(Alignment.Center)
    )
    IconButton(
        onClick = onSettingsPressed,
        modifier = Modifier.align(Alignment.CenterEnd)
    ) {
        Icon(Icons.Filled.AccountCircle, contentDescription = "Settings",
            tint = Color(0xFF180F3E)
        )
    }
}
}

