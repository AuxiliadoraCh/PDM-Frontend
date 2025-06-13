package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.ui.theme.Purple40

@Composable
fun PromotionDetailSheet(promotion: PromotionItem, onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(promotion.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = promotion.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Description:", color = Purple40)
        Text(promotion.description)
        Spacer(modifier = Modifier.height(8.dp))
        if (promotion.restaurantList.isNotEmpty()) {
            Text("Restaurants List:")
            promotion.restaurantList.forEach {
                Text("• $it")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("CODE:", fontWeight = FontWeight.Bold, color = Purple40)
        Text(promotion.couponCode, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            OutlinedButton(onClick = { }) {
                Text("Copy Code")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = onClose) {
                Text("Close")
            }
        }
    }
}
