package com.sedogapps.gidertablom.room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseWithCategory(
    @Embedded val expenses: Expenses,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Categories
)
