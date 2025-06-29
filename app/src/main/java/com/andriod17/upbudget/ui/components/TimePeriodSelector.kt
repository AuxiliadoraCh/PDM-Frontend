package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun TimePeriodSelector(
    selectedPeriod: String,
    onPeriodSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showAllMonths by rememberSaveable { mutableStateOf(false) }
    var currentState by remember { mutableStateOf(selectedPeriod) }

    val timePeriods = listOf("Current Week", "Current Month", "Last Month", "Last 6 Months")

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    val last6Months = (0..5).map {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, -it)
        val month = cal.get(Calendar.MONTH) // base 0
        val year = cal.get(Calendar.YEAR)
        "${getMonthName(month)} $year"
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                    if (currentState == "Last 6 Months" && !showAllMonths) {
                        showAllMonths = true
                    }
                }
                .padding(16.dp)
        ) {
            Text(
                text = currentState,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp)
        ) {
            timePeriods.forEach { period ->
                DropdownMenuItem(
                    text = { Text(period) },
                    onClick = {
                        currentState = period
                        onPeriodSelected(period)
                        if (period == "Last 6 Months") {
                            showAllMonths = true
                        } else {
                            expanded = false
                            showAllMonths = false
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (showAllMonths) {
                last6Months.forEach { month ->
                    DropdownMenuItem(
                        text = { Text(month) },
                        onClick = {
                            currentState = month
                            onPeriodSelected(month)
                            expanded = false
                            showAllMonths = false
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

fun getMonthName(month: Int): String {
    return when (month) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> "Unknown"
    }
}

@Preview(showBackground = true)
@Composable
fun TimePeriodSelectorPreview() {
    TimePeriodSelector(
        selectedPeriod = "Mes Actual",
        onPeriodSelected = { selectedPeriod -> /* Actualiza el periodo seleccionado */ }
    )
}
