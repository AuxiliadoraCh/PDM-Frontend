package com.andriod17.upbudget.data.model.Budgets

data class Budgets(
    val id: Int = 0,
    val user_id : String,
    val category_id : Int,
    val month : Int,
    val year: Int,
    val amount : Double
)