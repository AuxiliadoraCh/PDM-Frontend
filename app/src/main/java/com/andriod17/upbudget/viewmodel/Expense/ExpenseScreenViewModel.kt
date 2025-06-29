package com.andriod17.upbudget.viewmodel.Expense

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.data.model.Expense.TransactionTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ExpenseScreenViewModel(initialTabIndex: Int = 0) : ViewModel() {
    private val _incomeTabState = MutableStateFlow(ExpenseUi(selectedTabIndex = TransactionTab.Income.ordinal, isIncome = TransactionTab.Income.isIncome))
    private val _expenseTabState = MutableStateFlow(ExpenseUi(selectedTabIndex = TransactionTab.Expense.ordinal, isIncome = TransactionTab.Expense.isIncome))

    private val _uiState = MutableStateFlow(
        if (initialTabIndex == TransactionTab.Income.ordinal) _incomeTabState.value else _expenseTabState.value
    )
    val uiState: StateFlow<ExpenseUi> = _uiState.asStateFlow()

    private val _allExpenses = MutableStateFlow<List<ExpenseUi>>(emptyList())
    val allExpenses: StateFlow<List<ExpenseUi>> = _allExpenses.asStateFlow()

    private val _selectedPeriod = MutableStateFlow("Current Month")
    val selectedPeriod: StateFlow<String> = _selectedPeriod.asStateFlow()

    val filteredExpenses: StateFlow<List<ExpenseUi>> =
        combine(_allExpenses, _selectedPeriod) { allExpenses, period ->
            getFilteredExpensesByPeriodLogic(period, allExpenses)
        }.stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val incomeTotal: StateFlow<Double> = filteredExpenses.map { expenses ->
        expenses.filter { it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    val expenseTotal: StateFlow<Double> = filteredExpenses.map { expenses ->
        expenses.filter { !it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    val balance: StateFlow<Double> = combine(incomeTotal, expenseTotal) { income, expense ->
        income - expense
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    private fun updateActiveTabState(update: (ExpenseUi) -> ExpenseUi) {
        val currentIsIncome = _uiState.value.isIncome
        if (currentIsIncome) {
            _incomeTabState.update(update)
            _uiState.value = _incomeTabState.value
        } else {
            _expenseTabState.update(update)
            _uiState.value = _expenseTabState.value
        }
    }


    fun onAmountChange(amount: String) {
        updateActiveTabState { it.copy(amount = amount) }
    }

    fun onPaymentMethodChange(paymentMethod: String) {
        updateActiveTabState { it.copy(paymentMethod = paymentMethod) }
    }

    fun onPlaceChange(place: String) {
        updateActiveTabState { it.copy(place = place) }
    }

    fun onCategoryChange(category: String) {
        updateActiveTabState { it.copy(category = category) }
    }

    fun onDescriptionChange(description: String) {
        updateActiveTabState { it.copy(description = description) }
    }

    fun showDatePicker() {
        updateActiveTabState { it.copy(showDatePicker = true) }
    }

    fun hideDatePicker() {
        updateActiveTabState { it.copy(showDatePicker = false) }
    }

    fun onDateSelected(millis: Long?) {
        updateActiveTabState {
            val formattedDate = millis?.let { dateMillis ->
                val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                utcCalendar.timeInMillis = dateMillis
                val year = utcCalendar.get(Calendar.YEAR)
                val month = utcCalendar.get(Calendar.MONTH)
                val day = utcCalendar.get(Calendar.DAY_OF_MONTH)
                val localCalendar = Calendar.getInstance()
                localCalendar.set(Calendar.YEAR, year)
                localCalendar.set(Calendar.MONTH, month)
                localCalendar.set(Calendar.DAY_OF_MONTH, day)
                localCalendar.set(Calendar.HOUR_OF_DAY, 0)
                localCalendar.set(Calendar.MINUTE, 0)
                localCalendar.set(Calendar.SECOND, 0)
                localCalendar.set(Calendar.MILLISECOND, 0)
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                formatter.timeZone = TimeZone.getDefault()
                formatter.format(localCalendar.time)
            } ?: ""
            it.copy(date = formattedDate, showDatePicker = false, selectedDateMillis = millis)
        }
    }

    fun onTabSelected(newTabIndex: Int) {
        val currentUiStateValue = _uiState.value

        if (currentUiStateValue.isIncome) {
            _incomeTabState.value = currentUiStateValue
        } else {
            _expenseTabState.value = currentUiStateValue
        }

        val newIsIncome = TransactionTab.entries[newTabIndex].isIncome
        val targetTabState = if (newIsIncome) _incomeTabState.value else _expenseTabState.value

        _uiState.value = targetTabState.copy(
            selectedTabIndex = newTabIndex,
            isIncome = newIsIncome,
            showDatePicker = false
        )
    }


    fun onPeriodSelected(period: String) {
        _selectedPeriod.update { period }
    }

    fun saveExpense() {
        val currentUiState = _uiState.value

        if (currentUiState.amount.isBlank() || currentUiState.category.isBlank() || currentUiState.date.isBlank()) {
            Log.e("ExpenseScreenViewModel", "Cannot save expense: Amount, category, or date is blank.")
            return
        }

        val newExpense = ExpenseUi(
            amount = currentUiState.amount,
            category = currentUiState.category,
            description = currentUiState.description,
            isIncome = currentUiState.isIncome,
            date = currentUiState.date,
            paymentMethod = currentUiState.paymentMethod,
            place = currentUiState.place,
            selectedDateMillis = currentUiState.selectedDateMillis
        )

        _allExpenses.update { it + newExpense }

        _incomeTabState.value = ExpenseUi(
            selectedTabIndex = TransactionTab.Income.ordinal,
            isIncome = TransactionTab.Income.isIncome
        )
        _expenseTabState.value = ExpenseUi(
            selectedTabIndex = TransactionTab.Expense.ordinal,
            isIncome = TransactionTab.Expense.isIncome
        )

        _uiState.value = if (currentUiState.isIncome) _incomeTabState.value else _expenseTabState.value
    }

    internal fun setExpensesForPreview(expenses: List<ExpenseUi>) {
        _allExpenses.value = expenses
    }

    fun clearStates() {
        _incomeTabState.value = ExpenseUi(selectedTabIndex = TransactionTab.Income.ordinal, isIncome = TransactionTab.Income.isIncome)
        _expenseTabState.value = ExpenseUi(selectedTabIndex = TransactionTab.Expense.ordinal, isIncome = TransactionTab.Expense.isIncome)

        val currentActiveTabWasIncome = _uiState.value.isIncome
        _uiState.value = if (currentActiveTabWasIncome) _incomeTabState.value else _expenseTabState.value
    }

    private fun getFilteredExpensesByPeriodLogic(period: String, expenses: List<ExpenseUi>): List<ExpenseUi> {
        val currentDate = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormatter.timeZone = TimeZone.getDefault()

        val monthYearRegex = Regex("^(January|February|March|April|May|June|July|August|September|October|November|December) \\d{4}")
        if (monthYearRegex.matches(period)) {
            val parts = period.split(" ")
            if (parts.size == 2) {
                val monthName = parts[0]
                val year = parts[1].toIntOrNull() ?: return emptyList()
                val month = when (monthName) {
                    "January" -> 0
                    "February" -> 1
                    "March" -> 2
                    "April" -> 3
                    "May" -> 4
                    "June" -> 5
                    "July" -> 6
                    "August" -> 7
                    "September" -> 8
                    "October" -> 9
                    "November" -> 10
                    "December" -> 11
                    else -> return emptyList()
                }
                return expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    if (expenseDate != null) {
                        val cal = Calendar.getInstance()
                        cal.time = expenseDate
                        cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year
                    } else false
                }.sortedByDescending {
                    try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                }
            }
        }

        return when (period) {
            "Current Week" -> {
                val startOfWeek = getStartOfWeek(currentDate)
                val endOfWeek = getEndOfWeek(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfWeek) && !expenseDate.after(endOfWeek)
                }.sortedByDescending {
                    try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                }
            }
            "Current Month" -> {
                val startOfMonth = getStartOfMonth(currentDate)
                val endOfMonth = getEndOfMonth(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfMonth) && !expenseDate.after(endOfMonth)
                }.sortedByDescending {
                    try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                }
            }
            "Last Month" -> {
                val startOfLastMonth = getStartOfLastMonth(currentDate)
                val endOfLastMonth = getEndOfLastMonth(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfLastMonth) && !expenseDate.after(endOfLastMonth)
                }.sortedByDescending {
                    try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                }
            }
            "Last 6 Months" -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.MONTH, -6)
                val start6MonthsAgo = calendar.time

                val endCalendar = Calendar.getInstance()
                endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                val endOfCurrentMonth = endCalendar.time

                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(start6MonthsAgo) && !expenseDate.after(endOfCurrentMonth)
                }.sortedByDescending {
                    try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                }
            }
            else -> expenses.sortedByDescending {
                try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
            }
        }
    }

    private fun getStartOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    private fun getEndOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }

    private fun getStartOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    private fun getEndOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
    private fun getStartOfLastMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    private fun getEndOfLastMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
}