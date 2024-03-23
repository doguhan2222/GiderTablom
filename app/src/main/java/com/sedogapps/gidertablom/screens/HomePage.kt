package com.sedogapps.gidertablom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sedogapps.gidertablom.R
import com.sedogapps.gidertablom.components.CircularImage
import com.sedogapps.gidertablom.components.ListItemWithCard
import com.sedogapps.gidertablom.names
import com.sedogapps.gidertablom.ui.theme.CardColor
import com.sedogapps.gidertablom.ui.theme.CategorizedLazyColumnTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun HomePage() {

    val items2 = listOf("Today", "This Week", "This Month", "Calender")
    var selectedItem by remember { mutableStateOf<String?>("Today") }
    val namesList = names.map {

        Category(
            name = it.key.toString(),
            items = it.value
        )
    }
    Scaffold(
        topBar = {
            null
        }
    ) {
        it
        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp)

            )
            {
                CircularImage(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Hey,its me.",
                    imageSize = 120,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Good morning, Jon",
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Track your expenses , start your day right",
                        style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))


            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
            ) {
                items(items2) { item ->

                    ListItemWithCard(
                        isSelected = selectedItem == item, // Öğenin seçilme durumunu kontrol ediyoruz
                        item = item,
                        onItemClick = { clickedItem ->
                            // Tıklanan öğe seçili öğe olarak ayarlanıyor
                            selectedItem = clickedItem
                        }
                    )

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp) // İsteğe bağlı olarak padding ekleyebilirsiniz
                    .background(CardColor, RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Total Expenses",
                        style = TextStyle(
                            color = Color.White,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Icon(
//                            painter =  painterResource(id = R.drawable.turkish_lira),// Kullanmak istediğiniz ikon
//                            tint = Color.Unspecified,
//                            contentDescription = null, // Opsiyonel: Erişilebilirlik için içerik açıklaması
//                            modifier = Modifier.size(55.dp) // Icon boyutunu ayarlayın
//                        )
                        Text(
                            text = "2456.55  ₺",
                            style = TextStyle(color = Color.White, fontSize = 35.sp),
                            modifier = Modifier.padding(
                                end = 8.dp,
                                bottom = 10.dp
                            ) // İsteğe bağlı olarak alt boşluk ve sağ boşluk ekleyebilirsiniz
                        )


                    }
                }

            }
            CategorizedLazyColumnTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategorizedLazyColumn(
                        categories = namesList
                    )
                }
            }
        }

    }
}

data class Category(
    val name: String,
    val items: List<String>
)

@Composable
private fun CategoryHeader(
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
private fun CategoryItem(
    text: String,
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
            text = text,
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
private fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier.fillMaxHeight()
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.name)
            }
            items(category.items) { text ->
                CategoryItem(text)
            }
        }
    }
}

