package com.andriod17.upbudget.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.remote.RetrofitInstance
import com.andriod17.upbudget.data.remote.services.AuthService
import com.andriod17.upbudget.data.repository.Auth.AuthRepositoryImpl
import com.andriod17.upbudget.ui.components.AuthTopSection
import com.andriod17.upbudget.ui.components.CustomTextField
import com.andriod17.upbudget.ui.components.GoogleSignInButton
import com.andriod17.upbudget.ui.components.PrimaryActionButton
import com.andriod17.upbudget.ui.components.SignInPrompt
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.viewmodel.Auth.Login.LoginViewModel
import com.andriod17.upbudget.viewmodel.Auth.Login.LoginViewModelFactory

@Composable
fun LoginScreen(
    navController: NavController,
    onForgotPasswordClick: () -> Unit = {},
) {
    val context = LocalContext.current

    val authService: AuthService = remember { RetrofitInstance.authService }
    val authRepository = remember { AuthRepositoryImpl(authService) }
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory( authRepository = authRepository, sessionManager = SessionManager(context))
    )
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                    value = email,
                    onValueChange = { email = it }
                )

                CustomTextField(
                    label = "Password",
                    value = password,
                    isPassword = true,
                    onValueChange = { password = it }
                )

                Text(
                    text = "Forgot Password?",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp)
                        .clickable { onForgotPasswordClick() },
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF211557)
                    )
                )

                PrimaryActionButton(
                    text = "Sign in",
                    onClick = { viewModel.login(email, password, { navController.navigate(HomeNavigation) }) },
                )

                Text(
                    text = "Or sign in with",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )

                GoogleSignInButton(
                    text = "Sign in with Google",
                    onClick = { viewModel.logInWithGoogle() }
                )

                SignInPrompt(
                    promptText = "Don't have an account?",
                    actionText = "Sign up",
                    onActionClick = { viewModel.onRegisterPromptClick(navController) }
                )
            }
        }
    }
}
