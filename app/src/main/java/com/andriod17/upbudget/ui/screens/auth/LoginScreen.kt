package com.andriod17.upbudget.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.components.*
import com.andriod17.upbudget.viewmodel.Login.LoginViewModel
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    val context = LocalContext.current
    // val userRepository = UserRepository(AppDatabase.getInstance(context).userDao())
    // val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(userRepository))
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(19.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        AuthTopSection(
            iconResId = R.drawable.icon_up_budget,
            title = "Sign in",
            subtitle = "Hey there!\n Great to see you again"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            CustomTextField(
                label = "Email",
                value = state.email,
                onValueChange = viewModel::onEmailChange
            )

            CustomTextField(
                label = "Password",
                value = state.password,
                isPassword = true,
                onValueChange = viewModel::onPasswordChange
            )

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp)
                    .clickable { onForgotPasswordClick() },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    color = Color(0xFF211557),
                    fontWeight = FontWeight.Medium
                )
            )

            PrimaryActionButton(
                text = if (state.isLoading) "Logging in..." else "Sign in",
                onClick = viewModel::loginUser
            )

            Text(
                text = "Or sign in with",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )

            GoogleSignInButton(
                text = "Sign in with Google",
                onClick = viewModel::logInWithGoogle
            )

            SignInPrompt(
                promptText = "Don't have an account?",
                actionText = "Sign up",
                onActionClick = onRegisterClick
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
    )
}