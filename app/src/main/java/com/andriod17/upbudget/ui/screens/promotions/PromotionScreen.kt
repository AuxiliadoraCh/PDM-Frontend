package com.andriod17.upbudget.ui.screens.promotions

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.ui.components.CouponCard
import com.andriod17.upbudget.ui.components.CustomScaffold

@Composable
fun PromotionsScreen() {
    val coupons = listOf(
        PromotionItem(
            title = "Food Coupon",
            subtitle = "5% off in eligible restaurants",
            description = "Only for selected restaurants",
            imageResId = R.drawable.foodcupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        PromotionItem(
            title = "Clothing Coupon",
            subtitle = "Free accessory at select stores",
            description = "With purchases over $25",
            imageResId = R.drawable.clothescupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        PromotionItem(
            title = "Pharmacy Coupon",
            subtitle = "Get a free supplement sample",
            description = "On your next purchase",
            imageResId = R.drawable.pharmacycupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        PromotionItem(
            title = "Beauty Coupon",
            subtitle = "10% off in eligible brands",
            description = "Only this weekend",
            imageResId = R.drawable.makeupcupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        )
    )

    CustomScaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Promotions & Discounts",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(coupons) { coupon ->
                    CouponCard(
                        title = coupon.title,
                        subtitle = coupon.subtitle,
                        description = coupon.description,
                        imageResId = coupon.imageResId,
                        onViewMoreClicked = {

                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PromotionScreenPreview(){
    PromotionsScreen()}
