package com.andriod17.upbudget.viewmodel.Expense

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.data.model.Expense.getCurrentDateCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ExpenseScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExpenseUi())
    val uiState: StateFlow<ExpenseUi> = _uiState

    private val _expenses = MutableStateFlow<List<ExpenseUi>>(emptyList())
    val expenses: StateFlow<List<ExpenseUi>> = _expenses

    val incomeTotal: Double
        get() = _expenses.value.filter { it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

    val expenseTotal: Double
        get() = _expenses.value.filter { !it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

    val balance: Double
        get() = incomeTotal - expenseTotal

    fun onAmountChange(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
    }

    fun onPaymentMethodChange(paymentMethod: String) {
        _uiState.value = _uiState.value.copy(paymentMethod = paymentMethod)
    }

    fun onPlaceChange(place: String) {
        _uiState.value = _uiState.value.copy(place = place)
    }

    fun onCategoryChange(category: String) {
        _uiState.value = _uiState.value.copy(category = category)
    }

    fun onDescriptionChange(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun onSaveDetailsChange(saveDetails: Boolean) {
        _uiState.value = _uiState.value.copy(saveExpense = saveDetails)
    }

    fun saveExpense(amount: String, category: String, description: String, isIncome: Boolean, date: String? = null) {
        if (amount.isBlank() || category.isBlank()) return

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = if (date != null) {
            try {
                dateFormatter.format(dateFormatter.parse(date) ?: Date())
            } catch (e: Exception) {
                dateFormatter.format(Date())
            }
        } else {
            val currentDate = getCurrentDateCompat()
            try {
                dateFormatter.format(dateFormatter.parse(currentDate) ?: Date())
            } catch (e: Exception) {
                dateFormatter.format(Date())
            }
        }
        val newExpense = ExpenseUi(
            amount = amount,
            category = category,
            description = description,
            isIncome = isIncome,
            date = formattedDate
        )
        _expenses.value += newExpense
    }

    private fun getStartOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        return calendar.time
    }

    private fun getEndOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        return calendar.time
    }

    private fun getStartOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }

    private fun getEndOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.time
    }

    private fun getStartOfLastMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }

    private fun getEndOfLastMonth(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.time
    }

    fun filterExpensesByPeriod(period: String) {
        val currentDate = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val filteredExpenses = when (period) {
            "Semana Actual" -> {
                val startOfWeek = getStartOfWeek(currentDate)
                val endOfWeek = getEndOfWeek(currentDate)
                _expenses.value.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && expenseDate in startOfWeek..endOfWeek
                }
            }
            "Mes Actual" -> {
                val startOfMonth = getStartOfMonth(currentDate)
                val endOfMonth = getEndOfMonth(currentDate)
                _expenses.value.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && expenseDate in startOfMonth..endOfMonth
                }
            }
            "Mes Anterior" -> {
                val startOfLastMonth = getStartOfLastMonth(currentDate)
                val endOfLastMonth = getEndOfLastMonth(currentDate)
                _expenses.value.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && expenseDate in startOfLastMonth..endOfLastMonth
                }
            }
            "Últimos 6 Meses" -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.MONTH, -6)
                val start6MonthsAgo = calendar.time

                val endCalendar = Calendar.getInstance()
                endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                val endOfCurrentMonth = endCalendar.time

                _expenses.value.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(start6MonthsAgo) && !expenseDate.after(endOfCurrentMonth)
                }
            }
            else -> _expenses.value
        }
        Log.d("ExpenseScreen", "Expenses filtered by period: $filteredExpenses")
        _expenses.value = filteredExpenses
    }

    fun getFilteredExpensesByPeriod(period: String, expenses: List<ExpenseUi>): List<ExpenseUi> {
        val currentDate = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

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
                }
            }
        }

        return when (period) {
            "Semana Actual" -> {
                val startOfWeek = getStartOfWeek(currentDate)
                val endOfWeek = getEndOfWeek(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfWeek) && !expenseDate.after(endOfWeek)
                }
            }
            "Mes Actual" -> {
                val startOfMonth = getStartOfMonth(currentDate)
                val endOfMonth = getEndOfMonth(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfMonth) && !expenseDate.after(endOfMonth)
                }
            }
            "Mes Anterior" -> {
                val startOfLastMonth = getStartOfLastMonth(currentDate)
                val endOfLastMonth = getEndOfLastMonth(currentDate)
                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(startOfLastMonth) && !expenseDate.after(endOfLastMonth)
                }
            }
            "Últimos 6 Meses" -> {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.MONTH, -6)
                val start6MonthsAgo = calendar.time

                val endCalendar = Calendar.getInstance()
                endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                val endOfCurrentMonth = endCalendar.time

                expenses.filter {
                    val expenseDate = try { dateFormatter.parse(it.date) } catch (e: Exception) { null }
                    expenseDate != null && !expenseDate.before(start6MonthsAgo) && !expenseDate.after(endOfCurrentMonth)
                }
            }
            else -> expenses
        }
    }
}