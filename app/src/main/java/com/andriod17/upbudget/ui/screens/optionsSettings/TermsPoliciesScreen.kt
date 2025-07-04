package com.andriod17.upbudget.ui.screens.optionsSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.components.CustomScaffold

@Composable
fun TermsAndConditionsScreen(
    navController: NavHostController
) {
    CustomScaffold(
        title = "Terms and Policies",
        showBackButton = true,
        navController = navController,
        content = { innerPadding ->
            TermsContent(Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun TermsContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(38.dp)
    ) {
        Text(
            text = "Acceptance of Terms and Conditions",
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            fontSize = 25.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        SelectionContainer {
            Column {
                Text(
                    text = "By using this application, you agree to comply with these terms and conditions.\nIf you do not agree, please do not use this app.\n",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "Use of Information:",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "The information provided by the user will be used solely for internal purposes of the application, such as improving your experience and functionalities.\n",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "Privacy and Security:",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "We are committed to protecting your personal information. We use reasonable security measures to protect your data.\n",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "Data Storage:",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "The data entered may be stored locally on your device and also on external servers if you give your consent.\n",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "Modifications:",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "We reserve the right to modify these terms at any time. Users will be notified in case of significant changes.\n",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "Contact:",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "If you have questions about our terms or policies, you can contact us at: upBudget@gmail.com",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen(navController = rememberNavController())
}
