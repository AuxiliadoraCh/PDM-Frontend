package com.andriod17.upbudget.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.components.*
import com.andriod17.upbudget.viewmodel.Auth.Login.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    onForgotPasswordClick: () -> Unit = {},
) {
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.loginState.collectAsState()

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
                    onValueChange = {{ email = it } }
                )

                CustomTextField(
                    label = "Password",
                    value = password,
                    isPassword = true,
                    onValueChange = { { password = it } }
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

                // Usamos el texto del botón y el estado de habilitación desde el ViewModel
                PrimaryActionButton(
                    text = "Sign in",
                    onClick = { viewModel.handleLoginButtonClick(email, password) },
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
