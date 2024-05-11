package com.sedogapps.gidertablom.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sedogapps.gidertablom.R
import com.sedogapps.gidertablom.components.CategorizedLazyColumn
import com.sedogapps.gidertablom.components.CircularImage
import com.sedogapps.gidertablom.components.ListItemWithCard
import com.sedogapps.gidertablom.model.CategoryListview
import com.sedogapps.gidertablom.ui.theme.CardColor
import com.sedogapps.gidertablom.ui.theme.CategorizedLazyColumnTheme
import com.sedogapps.gidertablom.viewmodel.HomePageViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController,viewModel: HomePageViewModel = hiltViewModel()) {

    val items2 = listOf("Today", "This Week", "This Month", "Calender")
    var selectedItem by remember { mutableStateOf<String?>("Today") }


    var expenseListWithCategory by remember { mutableStateOf(emptyList<CategoryListview>()) }
    LaunchedEffect(viewModel) {
        viewModel.getAllList()
    }
    val expenses by viewModel.expensesWithCategoriesList.observeAsState(emptyList())

    expenseListWithCategory = expenses

    Log.d("homepage",expenseListWithCategory.toList().toString())

//    val namesList = expenseListWithCategory.map {
//
//        CategoryListview2(
//            categoryName = it.categoryName ,
//            categoryExpenses = it.categoryExpenses
//        )
//    }


//    val namesList = names.map {
//
//        CategoryListview(
//            name = it.key.toString(),
//            items = it.value
//        )
//    }
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
                        categories = expenseListWithCategory
                    )
                }
            }
        }

    }
}



