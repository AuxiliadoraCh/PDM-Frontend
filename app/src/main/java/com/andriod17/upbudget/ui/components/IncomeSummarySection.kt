package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun IncomeSummarySection(
    incomeAmount: String,
    modifier: Modifier = Modifier,
    icon: ImageVector =Icons.Default.Star
) {
    Column(
        modifier = modifier.padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "Monthly Income",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF211557),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.width(6.dp))

            Icon(
                imageVector = icon,
                contentDescription = "Income Icon",
                tint = Color(0xFF211557),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = incomeAmount,
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                color = Color(0xFF211557)
            )
        )
    }
}
