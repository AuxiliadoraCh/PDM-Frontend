package com.andriod17.upbudget.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.R

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
        .width(412.dp)
        .height(917.dp)
        .background(
            color = Color(0xFFEDEAF5),
            shape = RoundedCornerShape(size = 25.dp)
        ),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
                painter = painterResource(id = R.drawable.icon_up_budget),
                contentDescription = "UpBudgetLogo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .offset(x = 141.dp, y = 393.dp)
                    .width(130.dp)
                    .height(130.dp)
        )

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}
