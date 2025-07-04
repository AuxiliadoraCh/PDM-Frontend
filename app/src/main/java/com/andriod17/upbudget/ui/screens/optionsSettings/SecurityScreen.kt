package com.andriod17.upbudget.ui.screens.optionsSettings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.components.CustomScaffold

@Composable
fun SecurityScreen(
    navController: NavHostController
) {
    CustomScaffold(
        title = "Seguridad",
        showBackButton = true,
        navController = navController,
        content = { innerPadding ->
            SecurityContent(Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun SecurityContent(modifier: Modifier) {
    var is2FAEnabled by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado
        Text(
            text = "Opciones de Seguridad",
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Autenticación de Dos Factores (2FA)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Autenticación de Dos Factores")
            Checkbox(
                checked = is2FAEnabled,
                onCheckedChange = { is2FAEnabled = it }
            )
        }

        // Cambiar Contraseña
        Button(
            onClick = {
                // Lógica para cambiar la contraseña
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Cambiar Contraseña")
        }

        // Verificación de sesión activa
        Button(
            onClick = {
                // Lógica para verificar la sesión activa
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Verificar Sesión Activa")
        }

        // Cerrar Sesión
        Button(
            onClick = {
                // Lógica para cerrar la sesión
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityScreenPreview() {
    SecurityScreen(navController = rememberNavController())
}
