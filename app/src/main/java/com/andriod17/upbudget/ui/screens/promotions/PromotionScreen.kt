package com.andriod17.upbudget.ui.screens.promotions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Promotion.Promotion
import com.andriod17.upbudget.ui.components.CouponCard
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.PromotionDetailSheet
import com.andriod17.upbudget.ui.theme.Purple40
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromotionsScreen(
    navController : NavHostController
) {
    val coupons = listOf(
        Promotion(
            title = "Food Coupon",
            subtitle = "5% off in eligible restaurants",
            description = "Only for selected restaurants",
            imageResId = R.drawable.foodcupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        Promotion(
            title = "Clothing Coupon",
            subtitle = "Free accessory at select stores",
            description = "With purchases over \$25",
            imageResId = R.drawable.clothescupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        Promotion(
            title = "Pharmacy Coupon",
            subtitle = "Get a free supplement sample",
            description = "On your next purchase",
            imageResId = R.drawable.pharmacycupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        ),
        Promotion(
            title = "Beauty Coupon",
            subtitle = "10% off in eligible brands",
            description = "Only this weekend",
            imageResId = R.drawable.makeupcupon,
            restaurantList = listOf("María's Food", "Eli's Pizzas", "Miss Rocío Restaurant", "Guillermo's Bar"),
            couponCode = "FOOD5"
        )
    )

    var selectedCoupon by remember { mutableStateOf<Promotion?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    CustomScaffold (
        navController = navController,
        content =
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize()

            ) {


                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)) {
                    Text(
                        text = "View used coupons >",
                        style = MaterialTheme.typography.labelMedium,
                        color = Purple40,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    listOf("For you", "News", "Close to you").forEach { label ->
                        OutlinedButton(
                            onClick = {},
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Purple40),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Purple40
                            ),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text(text = label)
                        }
                    }
                }


                Spacer(modifier = Modifier.height(12.dp))


                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(coupons) { coupon ->
                        CouponCard(
                            title = coupon.title,
                            subtitle = coupon.subtitle,
                            description = coupon.description,
                            imageResId = coupon.imageResId,
                            onViewMoreClicked = {
                                selectedCoupon = coupon
                                scope.launch { sheetState.show() }
                            }
                        )
                    }
                }
            }


            selectedCoupon?.let { coupon ->
                ModalBottomSheet(
                    onDismissRequest = { selectedCoupon = null },
                    sheetState = sheetState
                ) {
                    PromotionDetailSheet(promotion = coupon, onClose = {
                        scope.launch { sheetState.hide() }
                        selectedCoupon = null
                    })
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PromotionScreenPreview() {
    //PromotionsScreen()
}
