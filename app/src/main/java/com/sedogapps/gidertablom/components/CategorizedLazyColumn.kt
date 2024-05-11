package com.sedogapps.gidertablom.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sedogapps.gidertablom.R
import com.sedogapps.gidertablom.model.CategoryListview
import com.sedogapps.gidertablom.room.entities.Expenses

@Composable
 fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
 fun CategoryItem(
    text: Expenses,
    modifier: Modifier = Modifier
) {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.profile_pic),// Kullanmak istediğiniz ikon
            tint = Color.Unspecified,
            contentDescription = null, // Opsiyonel: Erişilebilirlik için içerik açıklaması
            modifier = Modifier.size(55.dp) // Icon boyutunu ayarlayın
        )
        Text(
            text = text.amount.toString(),
            fontSize = 14.sp,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
 fun CategorizedLazyColumn(
    categories: List<CategoryListview>,
    modifier: Modifier = Modifier.fillMaxHeight()
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            if (!category.categoryExpenses.isNullOrEmpty()) {
                stickyHeader {
                    CategoryHeader(category.categoryName!!)
                }
                items(category.categoryExpenses) { text ->
                    CategoryItem(text)
                }
            }
        }
    }

}

