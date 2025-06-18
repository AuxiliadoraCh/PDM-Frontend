package com.andriod17.upbudget.ui.screens.expenses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.ExpenseButton
import com.andriod17.upbudget.ui.components.OutlinedTextFieldWithDropdown
import com.andriod17.upbudget.ui.components.SaveExpenseCheckbox
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel
import com.andriod17.upbudget.viewmodel.Category.CategoryViewModel
@Composable
fun ExpenseScreen(
    expenseViewModel: ExpenseScreenViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {
    val expenseUiState by expenseViewModel.uiState.collectAsState()
    val categoryUiState by categoryViewModel.uiState.collectAsState()

    var isIncome by remember { mutableStateOf(false) }

    CustomScaffold(
        title = "New Expense",
    ) { innerPadding ->
        ExpenseScreenContent(
            padding = innerPadding,
            expenseUiState = expenseUiState,
            categories = categoryUiState.categories.map { it.name },
            onAmountChange = expenseViewModel::onAmountChange,
            onPaymentMethodChange = expenseViewModel::onPaymentMethodChange,
            onPlaceChange = expenseViewModel::onPlaceChange,
            onDescriptionChange = expenseViewModel::onDescriptionChange,
            onSaveDetailsChange = expenseViewModel::onSaveDetailsChange,
            onConfirmClick = {
                expenseViewModel.saveExpense(
                    expenseUiState.amount,
                    expenseUiState.category,
                    expenseUiState.description,
                    isIncome
                )
            },
            onCategoryChange = expenseViewModel::onCategoryChange,
            onIncomeChange = { isIncome = true },
            onExpenseChange = { isIncome = false }
        )
    }
}

@Composable
fun ExpenseScreenContent(
    padding: PaddingValues,
    expenseUiState: ExpenseUi,
    categories: List<String>,
    onAmountChange: (String) -> Unit,
    onPaymentMethodChange: (String) -> Unit,
    onPlaceChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveDetailsChange: (Boolean) -> Unit,
    onConfirmClick: () -> Unit,
    onCategoryChange: (String) -> Unit,
    onIncomeChange: () -> Unit, // Acción de ingreso
    onExpenseChange: () -> Unit // Acción de gasto
) {
    val paymentMethods = listOf("Cash", "Credit Card", "Bank Transfer")
    var expandedPaymentMethod by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.finance_expense),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .padding(top = 16.dp)
        )
        Text(
            text = "Date: ${expenseUiState.date}",
            fontFamily = FontFamily(Font(R.font.nunito_semibolditalic)),
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF35218A)
        )

        // Amount input
        OutlinedTextField(
            value = expenseUiState.amount,
            onValueChange = onAmountChange,
            label = { Text("Amount") },
            leadingIcon = { Text("$") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Payment method dropdown
        OutlinedTextFieldWithDropdown(
            value = expenseUiState.paymentMethod,
            onValueChange = {},
            label = "Payment Method",
            options = paymentMethods,
            expanded = expandedPaymentMethod,
            onExpandedChange = { expandedPaymentMethod = it },
            onOptionSelected = onPaymentMethodChange
        )

        // Place input
        OutlinedTextField(
            value = expenseUiState.place,
            onValueChange = onPlaceChange,
            label = { Text("Place") },
            modifier = Modifier.fillMaxWidth()
        )

        // Category dropdown
        OutlinedTextFieldWithDropdown(
            value = expenseUiState.category,
            onValueChange = {},
            label = "Category",
            options = categories,
            expanded = expandedCategory,
            onExpandedChange = { expandedCategory = it },
            onOptionSelected = onCategoryChange
        )

        // Description input
        OutlinedTextField(
            value = expenseUiState.description,
            onValueChange = onDescriptionChange,
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        SaveExpenseCheckbox(
            isChecked = expenseUiState.saveExpense,
            onCheckedChange = onSaveDetailsChange
        )

        // Radio buttons to select Income or Expense
        Row {
            Text("Income")
            RadioButton(selected = expenseUiState.isIncome, onClick = onIncomeChange)
            Spacer(modifier = Modifier.width(16.dp))
            Text("Expense")
            RadioButton(selected = !expenseUiState.isIncome, onClick = onExpenseChange)
        }

        ExpenseButton(isSaving = expenseUiState.isSaving, onClick = onConfirmClick)
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreen()
}
