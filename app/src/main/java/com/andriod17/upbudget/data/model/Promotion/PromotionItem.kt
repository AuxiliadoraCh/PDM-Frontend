package com.andriod17.upbudget.data.model.Promotion

import java.util.Date

    data class PromotionItem (
        val id: Int = 0,
        val title:String,
        val description: String,
        val active: Boolean,
        val start_date: Date,
        val end_date: Date,
        val images: List<String>,
        val restaurants: List<String>
    )







