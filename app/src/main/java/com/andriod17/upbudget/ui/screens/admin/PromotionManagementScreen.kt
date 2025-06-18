package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.components.AdminCouponCard
import com.andriod17.upbudget.ui.components.TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.andriod17.upbudget.ui.components.AdminBottomBar


@Composable
fun PromotionManagementScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedItem by remember { mutableStateOf("admin_home") }
    var couponList by remember {
        mutableStateOf(
            listOf(
                PromotionItem(
                    title = "Food Coupon",
                    subtitle = "2x1 Mother’s Day breakfasts at select restaurants",
                    description = "",
                    imageResId = R.drawable.foodcupon,
                    isActive = true
                ),
                PromotionItem(
                    title = "Pharmacy Coupon",
                    subtitle = "Get a free supplement sample",
                    description = "",
                    imageResId = R.drawable.pharmacycupon,
                    isActive = true
                )
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Promotions management",
            onBackPressed = {},
            onSettingsPressed = {}
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Find promotion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        )

        LazyColumn(modifier = Modifier
            .weight(1f) // 👈 Deja espacio para el BottomBar
            .padding(horizontal = 8.dp)) {
            items(couponList) { coupon ->
                AdminCouponCard(
                    title = coupon.title,
                    subtitle = coupon.subtitle,
                    imageResId = coupon.imageResId,
                    isActive = coupon.isActive,
                    onToggleActive = { newValue ->
                        couponList = couponList.map {
                            if (it.title == coupon.title) it.copy(isActive = newValue) else it
                        }
                    }
                )
            }
        }

        AdminBottomBar(
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PromotionManagementPreview(){
    PromotionManagementScreen()
}