package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Category.CategoryUi

@Composable
fun CategoryItem(category: CategoryUi) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = category.iconResId),
                contentDescription = category.name,
                tint = Color.Unspecified,
                modifier = Modifier.size(36.dp)
            )
        }
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            color = Color(0xFF180F3E),
            textAlign = TextAlign.Center
        )
    }
}