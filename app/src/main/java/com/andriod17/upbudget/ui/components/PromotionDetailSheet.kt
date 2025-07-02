package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.ui.theme.Purple40

@Composable
fun PromotionDetailSheet(promotion: PromotionItem, onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {

        Text(
            text = promotion.title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        AsyncImage(
            model = promotion.imageResId ?: "https://via.placeholder.com/300x150.png?text=No+Image",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        )





        Text(
            text = "Description:",
            style = MaterialTheme.typography.labelMedium.copy(color = Purple40),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = promotion.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        if (promotion.restaurantList.isNotEmpty()) {
            Text(
                text = "Restaurants List:",
                style = MaterialTheme.typography.labelMedium.copy(color = Purple40),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp, bottom = 24.dp)) {
                promotion.restaurantList.forEach {
                    Text(text = "• $it", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Text(
            text = "CODE:",
            style = MaterialTheme.typography.labelMedium.copy(color = Purple40),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = promotion.couponCode,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { /* TODO: implementar copia */ },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text("Copy Code")
            }

            OutlinedButton(
                onClick = onClose,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple40)
            ) {
                Text("Close")
            }
        }
    }
}
