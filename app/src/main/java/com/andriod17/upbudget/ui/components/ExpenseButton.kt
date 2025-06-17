package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseButton(
    isSaving: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !isSaving,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isSaving) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text("Confirm")
        }
    }
}
