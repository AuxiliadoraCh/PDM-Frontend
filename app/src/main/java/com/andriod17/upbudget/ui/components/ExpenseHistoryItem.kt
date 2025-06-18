package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpenseHistoryItem(expense: ExpenseUi) {
    val color = if (expense.isIncome) Color(0xFF4CAF50) else Color(0xFFF44336) // Verde o Rojo
    @Suppress("DEPRECATION") val formattedDate = SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(expense.date))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ) {
            Text(text = expense.category)
            Text(text = formattedDate, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "$${expense.amount}", fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(60.dp)
                    .padding(top = 2.dp)
                    .background(color)
            )
        }
    }
}
