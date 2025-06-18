package com.andriod17.upbudget.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.components.AuthTopSection
import com.andriod17.upbudget.ui.components.CustomTextField
import com.andriod17.upbudget.ui.components.GoogleSignInButton
import com.andriod17.upbudget.ui.components.PrimaryActionButton
import com.andriod17.upbudget.ui.components.SignInPrompt
import com.andriod17.upbudget.viewmodel.Register.RegisterViewModel
//import com.andriod17.upbudget.viewmodel.Register.RegisterViewModelFactory


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onSignUpClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {},
    onSignInClick: () -> Unit = {}
) {
    val context = LocalContext.current
    //val userRepository = UserRepository(AppDatabase.getInstance(context).userDao())
    //val viewModel: RegisterViewModel = viewModel(factory = RegisterViewModelFactory(userRepository))
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
            title = "Sign up",
            subtitle = "Create an account"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CustomTextField(label = "Username", value = state.username, onValueChange =  viewModel::onUsernameChange)
            CustomTextField(label = "Email", value = state.email, onValueChange = viewModel::onEmailChange)
            CustomTextField(label = "Password", value = state.password, isPassword = true, onValueChange = viewModel::onPasswordChange)

            PrimaryActionButton(text = if (state.isLoading) "Registering..." else "Sign up", onClick = onSignUpClick)

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
                onActionClick = onSignInClick
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}