package com.andriod17.upbudget.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R


@Composable
fun OnboardingInfoScreen2() {
    Box(
        modifier = Modifier
            .width(412.dp)
            .height(917.dp)
            .background(
                color = Color(0xFFFFFFFF),
            )
            .padding(25.dp)
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = R.drawable.investing),
                contentDescription = "Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(250.dp)
                    .width(300.dp)
            )

            Text(
                text = "Budget Smarter",

                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,

                    )
            )


                Text(
                    text = "Create simple budgets, set financial goals, and get reminders to stay on track.",

                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )


            Image(
                painter = painterResource(id = R.drawable.pagination),
                contentDescription = "Pagination Dots",
                modifier = Modifier
                    .width(60.dp)
                    .height(10.dp)
            )

            Column(
                modifier = Modifier.height(110.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { /* TODO: Navegar al siguiente */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF216B8A)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(47.dp)
                        .width(117.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Next",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_light)),
                                fontWeight = FontWeight(400),
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Next Icon",
                            tint = Color.White
                        )

                    }
                }

                TextButton(onClick = { }) {
                    Text(
                        text = "Skip",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_light)),
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF16475C),
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }


            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingInfoScreen2Preview() {
    OnboardingInfoScreen2()
}