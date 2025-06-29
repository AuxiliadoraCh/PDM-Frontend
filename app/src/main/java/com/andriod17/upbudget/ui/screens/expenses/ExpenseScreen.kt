package com.andriod17.upbudget.ui.screens.expenses

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.Button
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.ExpenseButton
import com.andriod17.upbudget.ui.components.OutlinedTextFieldWithDropdown
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel
import com.andriod17.upbudget.viewmodel.Category.CategoryViewModel
import java.util.Calendar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState


@Composable
fun ExpenseScreen(
    expenseViewModel: ExpenseScreenViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {
    val expenseUiState by expenseViewModel.uiState.collectAsState()
    val categoryUiState by categoryViewModel.uiState.collectAsState()

    val navController = rememberNavController()

    val onDateChange = expenseViewModel::onDateChange
    CustomScaffold(
        title = "New Transaction", navController = navController
    ) { innerPadding ->
        ExpenseScreenContent(
            padding = innerPadding,
            expenseUiState = expenseUiState,
            categories = categoryUiState.categories.map { it.name },
            onAmountChange = expenseViewModel::onAmountChange,
            onPaymentMethodChange = expenseViewModel::onPaymentMethodChange,
            onPlaceChange = expenseViewModel::onPlaceChange,
            onDescriptionChange = expenseViewModel::onDescriptionChange,
            onConfirmClick = {
                expenseViewModel.saveExpense(
                    expenseUiState.amount,
                    expenseUiState.category,
                    expenseUiState.description,
                    expenseUiState.isIncome,
                    expenseUiState.date
                )
            },
            onCategoryChange = expenseViewModel::onCategoryChange,
            onIncomeChange = { expenseViewModel.onIncomeChange(true) },
            onExpenseChange = { expenseViewModel.onIncomeChange(false) },
            onDateClick = onDateChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreenContent(
    padding: PaddingValues,
    expenseUiState: ExpenseUi,
    categories: List<String>,
    onAmountChange: (String) -> Unit,
    onPaymentMethodChange: (String) -> Unit,
    onPlaceChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onCategoryChange: (String) -> Unit,
    onIncomeChange: () -> Unit,
    onExpenseChange: () -> Unit,
    onDateClick: (String) -> Unit
) {
    val paymentMethods = listOf("Cash", "Credit Card", "Bank Transfer")
    val context = androidx.compose.ui.platform.LocalContext.current

    var showDatePicker by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        LaunchedEffect(Unit) {
            keyboardController?.hide()
        }

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
                keyboardController?.show()
            },
            confirmButton = {
                Button(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val date = Calendar.getInstance().apply { timeInMillis = millis }
                        val formattedDate = formatter.format(date.time)
                        onDateClick(formattedDate)
                    }
                    showDatePicker = false
                    keyboardController?.show()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDatePicker = false
                    keyboardController?.show()
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            LaunchedEffect(isPressed) {
                if (isPressed) {
                    showDatePicker = true
                }
            }

            OutlinedTextField(
                value = expenseUiState.date,
                onValueChange = {},
                readOnly = true,
                label = { Text("Date") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "Select date",
                        modifier = Modifier.clickable { showDatePicker = true }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                interactionSource = interactionSource
            )
        }

        val amountFocusRequester = remember { FocusRequester() }
        val paymentFocusRequester = remember { FocusRequester() }
        val placeFocusRequester = remember { FocusRequester() }
        val categoryFocusRequester = remember { FocusRequester() }
        val descriptionFocusRequester = remember { FocusRequester() }

        OutlinedTextField(
            value = expenseUiState.amount,
            onValueChange = onAmountChange,
            label = { Text("Amount") },
            leadingIcon = { Text("$") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    paymentFocusRequester.requestFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(amountFocusRequester)
        )

        OutlinedTextFieldWithDropdown(
            value = expenseUiState.paymentMethod,
            onValueChange = onPaymentMethodChange,
            label = "Payment Method",
            options = paymentMethods,
            onOptionSelected = {
                onPaymentMethodChange(it)
                placeFocusRequester.requestFocus()
            },
            modifier = Modifier.focusRequester(paymentFocusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    placeFocusRequester.requestFocus()
                }
            )
        )

        OutlinedTextField(
            value = expenseUiState.place,
            onValueChange = onPlaceChange,
            label = { Text("Place") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next), // Cambiado a Text
            keyboardActions = KeyboardActions(
                onNext = {
                    categoryFocusRequester.requestFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(placeFocusRequester)
        )

        OutlinedTextFieldWithDropdown(
            value = expenseUiState.category,
            onValueChange = onCategoryChange,
            label = "Category",
            options = categories,
            onOptionSelected = {
                onCategoryChange(it)
                descriptionFocusRequester.requestFocus()
            },
            modifier = Modifier.focusRequester(categoryFocusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    descriptionFocusRequester.requestFocus()
                }
            )
        )

        OutlinedTextField(
            value = expenseUiState.description,
            onValueChange = onDescriptionChange,
            label = { Text("Description") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(descriptionFocusRequester)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onIncomeChange() }
            ) {
                Text("Income")
                RadioButton(selected = expenseUiState.isIncome, onClick = { onIncomeChange() })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onExpenseChange() }
            ) {
                Text("Expense")
                RadioButton(selected = !expenseUiState.isIncome, onClick = { onExpenseChange() })
            }
        }

        ExpenseButton(isSaving = expenseUiState.isSaving, onClick = {
            onConfirmClick()
            focusManager.clearFocus()
            keyboardController?.hide()
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreen()
}