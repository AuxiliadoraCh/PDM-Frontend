package com.andriod17.upbudget.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import com.andriod17.upbudget.ui.navigation.LoginNavigation
import com.andriod17.upbudget.viewmodel.Auth.Login.LoginViewModel
import com.andriod17.upbudget.viewmodel.Auth.Login.LoginViewModelFactory
import com.andriod17.upbudget.viewmodel.Auth.Register.RegisterViewModel
import com.andriod17.upbudget.viewmodel.Auth.Register.RegisterViewModelFactory

@Composable
fun RegisterScreen(
    navController: NavController,
    onGoogleSignInClick: () -> Unit = {},
) {
    val context = LocalContext.current

    val authService: AuthService = remember { RetrofitInstance.authService }
    val authRepository = remember { AuthRepositoryImpl(authService) }
    val viewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory( authRepository = authRepository, sessionManager = SessionManager(context))
    )
    var username by remember { mutableStateOf("") }
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
                title = "Sign up",
                subtitle = "Create an account"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                CustomTextField(label = "Username", value = username, onValueChange = { username = it })
                CustomTextField(label = "Email", value = email, onValueChange = { email = it })
                CustomTextField(label = "Password", value = password, isPassword = true, onValueChange = { password = it })

                PrimaryActionButton(
                    text = "Sign up",
                    onClick = {
                        viewModel.registerUser(
                            email = email,
                            password = password,
                            onRegistrationSuccess = {
                                navController.navigate(HomeNavigation) {
                                    popUpTo(LoginNavigation) { inclusive = true }

                                }
                            }
                        )
                    }
                )

                Text(
                    text = "Or sign up with",
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
                    text = "Sign up with Google",
                    onClick = onGoogleSignInClick
                )
                SignInPrompt(
                    promptText = "Already have an account?",
                    actionText = "Sign in",
                    onActionClick = { navController.navigate(LoginNavigation) }
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    //RegisterScreen()
}