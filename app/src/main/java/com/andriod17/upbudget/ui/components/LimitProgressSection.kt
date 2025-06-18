package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.andriod17.upbudget.R

@Composable
fun LimitProgressSection(
    percentage: Int,
    progress: Float,
    valueReached: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(14.dp)
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$percentage%",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibolditalic)),
                fontWeight = FontWeight.Medium,
                color = Color(0xFF35218A)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "of limit reached",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibolditalic)),
                fontWeight = FontWeight.Medium,
                color = Color(0xFF35218A)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        BoxWithConstraints {
            val totalWidth = maxWidth
            val labelWidth = 60.dp
            val availableWidth = totalWidth - labelWidth
            val offsetX = (availableWidth * progress).coerceAtMost(availableWidth)

            Column {
                Box {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(62.dp)),
                        color = Color(0xFF35218A),
                        trackColor = Color.Transparent,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .padding(horizontal = 7.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(4) {
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .background(
                                        Color(0xFF35218A),
                                        shape = RoundedCornerShape(50)
                                    )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))
                Column(
                    modifier = Modifier.offset(x = offsetX),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.tooltipcaretitem),
                        contentDescription = "Indicator Pointer",
                        modifier = Modifier.size(14.dp),
                        tint = Color(0xFF35218A)
                    )
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, RoundedCornerShape(6.dp))
                            .background(Color(0xFF35218A), RoundedCornerShape(6.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = valueReached,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
