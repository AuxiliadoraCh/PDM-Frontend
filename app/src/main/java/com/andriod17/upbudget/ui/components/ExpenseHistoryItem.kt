package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Category.CategoryUi
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.viewmodel.Category.CategoryViewModel
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Composable
fun ExpenseHistoryItem(expense: ExpenseUi, categoryViewModel: CategoryViewModel) {
    val isIncome = expense.isIncome // Si es ingreso
    val backgroundColor = if (isIncome) Color(0xFF4CAF50) else Color(0xFFBA1A1A) // Verde para ingresos, rojo para gastos

    val categoryIcon = categoryViewModel.getCategoryIcon(expense.category)

    val dateFormat = SimpleDateFormat("MMMM dd", Locale.ENGLISH)
    val date = try {
        val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(expense.date)
        if (parsedDate != null) {
            val formatted = dateFormat.format(parsedDate)
            formatted.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        } else {
            "Fecha inválida"
        }
    } catch (e: Exception) {
        "Fecha inválida"
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDEAF5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            categoryIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = expense.category,
                    modifier = Modifier.size(22.dp),
                    tint = Color(0xFF180F3E),
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = expense.category,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    ),
                    color = Color(0xFF180F3E),

                    )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily(Font(R.font.nunito_semibold))
                    ),
                    color = Color(0xFF180F3E),
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = (if (isIncome) "+ $" else "- $") + expense.amount,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        ),
                        color = if (isIncome) Color(0xFF4CAF50) else Color(0xFFBA1A1A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .fillMaxHeight()
                            .padding(0.dp)
                            .background(backgroundColor)
                    )
                }
            }

        }
    }
}

@Composable
fun PreviewExpenseHistoryItem() {
    val exampleExpense = ExpenseUi(
        amount = "100.00",
        paymentMethod = "Cash",
        place = "Supermarket",
        category = "Food",
        description = "Groceries",
        date = "2025-06-28",
        isSaving = false,
        isIncome = false, // Gasto
        showDatePicker = false,
        selectedTabIndex = 0,
        selectedDateMillis = null
    )

    val categoryViewModel: CategoryViewModel = viewModel()

    ExpenseHistoryItem(expense = exampleExpense, categoryViewModel = categoryViewModel)
}

@Preview(showBackground = true)
@Composable
fun PreviewExpenseHistoryScreen() {
    LazyColumn {
        items(1) { // Solo una entrada de ejemplo
            PreviewExpenseHistoryItem()
        }
    }
}
