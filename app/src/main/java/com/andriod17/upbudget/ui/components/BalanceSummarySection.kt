package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R

@Composable
fun BalanceSummarySection(
    balanceAmount: String,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = "Your Balance",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color(0xFF34218D),
                    textAlign = TextAlign.Center
                )
            )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =balanceAmount,
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                color = Color(0xFF211557)
            )
        )
    }
}

