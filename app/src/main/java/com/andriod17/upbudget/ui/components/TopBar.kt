package com.andriod17.upbudget.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {},
    showBackButton: Boolean = true,
    showSettingsIcon: Boolean = true,
    useOptionsIcon: Boolean = false,
    onOptionsClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = title,
                color = Color(0xFF180F3E),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
            )
        },
        navigationIcon = {
            // Mostrar el ícono de opciones si useOptionsIcon es verdadero
            if (useOptionsIcon) {
                IconButton(onClick = onOptionsClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Options",
                        tint = Color(0xFF180F3E)
                    )
                }
            } else if (showBackButton) {
                // Mostrar el ícono de retroceso si showBackButton es verdadero
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF180F3E)
                    )
                }
            }
        },
        actions = {
            // Mostrar el ícono de perfil siempre (settings)
            if (showSettingsIcon) {
                IconButton(onClick = onSettingsPressed) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color(0xFF180F3E)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF9F0FF),
            titleContentColor = Color(0xFF180F3E),
            navigationIconContentColor = Color(0xFF180F3E),
            actionIconContentColor = Color(0xFF180F3E)
        )
    )
}
