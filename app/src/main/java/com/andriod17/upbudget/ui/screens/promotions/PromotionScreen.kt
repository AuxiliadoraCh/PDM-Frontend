package com.andriod17.upbudget.ui.screens.promotions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.data.model.Promotion.toPromotionItem
import com.andriod17.upbudget.ui.components.CouponCard
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.PromotionDetailSheet
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.UsedCouponsNavigation
import com.andriod17.upbudget.ui.theme.Purple40
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromotionsScreen(
    navController: NavHostController
) {

    val viewModel: PromotionListViewModel = viewModel(factory = PromotionListViewModel.Factory)
    val promotions by viewModel.promotions.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val usedCouponsViewModel: UsedCouponsViewModel = viewModel(factory = UsedCouponsViewModel.Factory)

    LaunchedEffect(Unit) {
        viewModel.loadPromotions()
    }

    val coupons = promotions.map {it.toPromotionItem()}


    var selectedCoupon by remember { mutableStateOf<PromotionItem?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    CustomScaffold(
        navController = navController,
        onBackPressed = {
            navController.navigate(HomeNavigation)
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
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
                        OutlinedButton(
                            onClick = {

                            },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Purple40),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple40),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text("For you")
                        }

                        OutlinedButton(
                            onClick = {

                            },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Purple40),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple40),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text("News")
                        }

                        OutlinedButton(
                            onClick = {
                                navController.navigate(UsedCouponsNavigation)
                            },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Purple40),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Purple40),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text("Used coupons")
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
                        },
                            usedCouponsViewModel = usedCouponsViewModel)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PromotionScreenPreview() {
    // xd
}
