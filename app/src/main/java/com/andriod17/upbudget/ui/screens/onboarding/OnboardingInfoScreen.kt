package com.andriod17.upbudget.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.Composable
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.size
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import com.andriod17.upbudget.ui.navigation.OnboardingInfoNavigation
import com.andriod17.upbudget.ui.navigation.RegisterNavigation
import com.andriod17.upbudget.viewmodel.Onboarding.OnboardingInfoViewModel

@Composable
fun OnboardingInfoScreen(
    navController: NavController,
    viewModel: OnboardingInfoViewModel = viewModel()
) {
    val currentPage by viewModel.currentPage.collectAsState()
    val onboardingData = viewModel.onboardingData
    val page = onboardingData[currentPage]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Image(
            painter = painterResource(id = R.drawable.vector_wave_top),
            contentDescription = "Top Wave",
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = page.image),
                contentDescription = "Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(250.dp)
                    .width(300.dp)
            )

            Text(
                text = page.title,
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                text = page.description,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                onboardingData.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(10.dp)
                            .background(
                                if (index == currentPage) Color(0xFF216B8A) else Color.Gray,
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
            }

            Column(
                modifier = Modifier.height(110.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { viewModel.nextPage() },
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
                TextButton(onClick = { viewModel.skip(navController) }) {
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
        Image(
            painter = painterResource(id = R.drawable.vector_wave_bottom),
            contentDescription = "Bottom Wave",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(113.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingInfoScreenPreview() {
    //OnboardingInfoScreen()
}