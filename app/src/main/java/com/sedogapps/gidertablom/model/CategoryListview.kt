package com.sedogapps.gidertablom.model

import com.sedogapps.gidertablom.room.entities.Expenses

data class CategoryListview(// bu sınıf liste içinde kullanılıyor
    val categoryName: String?,
    val categoryExpenses: List<Expenses>?
)