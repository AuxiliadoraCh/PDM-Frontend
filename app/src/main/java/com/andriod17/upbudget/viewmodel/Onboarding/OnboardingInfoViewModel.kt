package com.andriod17.upbudget.viewmodel.Onboarding

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.navigation.RegisterNavigation

data class OnboardingContent(
    val image: Int,
    val title: String,
    val description: String
)

class OnboardingInfoViewModel : ViewModel() {
    private val _onboardingData = listOf(
        OnboardingContent(R.drawable.person_money, "Take Control", "Manage your money smarter,\n track your spending,\n and start saving with\n confidence."),
        OnboardingContent(R.drawable.investing, "Budget Smarter", "Create simple budgets,\n set financial goals, and get reminders to stay on track."),
        OnboardingContent(R.drawable.learn_save, "Learn & Save", "Get financial tips, unlock\n exclusive discounts, and\n grow your money every\n day.")
    )
    val onboardingData: List<OnboardingContent> = _onboardingData

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    val currentContent: OnboardingContent
        get() = _onboardingData[_currentPage.value]

    fun nextPage() {
        if (_currentPage.value < _onboardingData.size - 1) {
            _currentPage.value = _currentPage.value + 1
        }
    }

    fun skip(navController: NavController) {
        navController.navigate(RegisterNavigation)
    }
}
