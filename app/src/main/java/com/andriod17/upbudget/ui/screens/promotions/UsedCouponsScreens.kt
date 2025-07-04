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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.UsedCouponCard
import com.andriod17.upbudget.ui.theme.Purple40
import com.andriod17.upbudget.ui.theme.Purple80

@Composable
fun UsedCouponsScreen(navController: NavHostController) {
    val viewModel: UsedCouponsViewModel = viewModel (factory = UsedCouponsViewModel.Factory )
    val usedCoupons by viewModel.usedCoupons.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsedCoupons()
    }

    var selectedFilter by remember { mutableStateOf("Recent") }

    CustomScaffold(
        navController = navController,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Used coupons",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple40,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    listOf("Recent", "All").forEach { label ->
                        val isSelected = selectedFilter == label
                        OutlinedButton(
                            onClick = { selectedFilter = label },
                            border = BorderStroke(1.dp, Purple40),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) Purple80 else MaterialTheme.colorScheme.background,
                                contentColor = Purple40
                            ),
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f)
                        ) {
                            Text(text = label, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ){
                        items(usedCoupons) {item ->
                            UsedCouponCard(
                                title = item.promotion.title,
                                subtitle = item.promotion.description,
                                imageResId = item.promotion.images.firstOrNull(),
                                usedDate = item.usedAt
                            )
                        }
                    }

                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UsedScreenPreview(){
    //UsedCouponsScreen()
}



