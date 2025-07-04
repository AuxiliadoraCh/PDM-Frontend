package com.andriod17.upbudget.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.ui.screens.promotions.UsedCouponsViewModel
import com.andriod17.upbudget.ui.theme.Purple40

@Composable
fun PromotionDetailSheet(
    promotion: PromotionItem,
    onClose: () -> Unit,
    usedCouponsViewModel: UsedCouponsViewModel
) {
    val loading by usedCouponsViewModel.loading.collectAsState()
    val error by usedCouponsViewModel.error.collectAsState()
    val success by usedCouponsViewModel.success.collectAsState()
    val context = LocalContext.current



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
                onClick = {
                    usedCouponsViewModel.registerCouponUsage(promotion.id)
                    Toast.makeText(context, "Código copiado: ${promotion.couponCode}", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                enabled = !loading
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Copy Code")
                }
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