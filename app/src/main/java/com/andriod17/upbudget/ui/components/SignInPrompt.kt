package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R // Make sure R is imported

@Composable
fun SignInPrompt(onSignInClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append("Already have an account? ")
        pushStringAnnotation(tag = "SIGN_IN", annotation = "SIGN_IN")
        withStyle(style = SpanStyle(
            color = Color(0xFF211557),
            fontWeight = FontWeight.Bold
        )
        ) {
            append("Sign in")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            textAlign = TextAlign.Center,
            color = Color(0xFF180F3E)
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "SIGN_IN", start = offset, end = offset)
                .firstOrNull()?.let {
                    onSignInClick()
                }
        }
    )
}