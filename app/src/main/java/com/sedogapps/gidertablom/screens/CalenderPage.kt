package com.sedogapps.gidertablom.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.sedogapps.gidertablom.room.AppDatabase
import com.sedogapps.gidertablom.room.entities.Expenses
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.YearMonth

/* var x:String = "";
    if (calendarState.selectionState.selection.isNotEmpty()){
        x = calendarState.selectionState.selection.joinToString { it.toString() }
        calendarState.selectionState.selection = listOf()
        isCalendarVisible = !isCalendarVisible
    }*/

@Composable
fun CalenderPage(navController: NavHostController) {
    val context = LocalContext.current

//    val namesList = names.map {
//
//        CategoryListview(
//            name = it.key.toString(),
//            items = it.value
//        )
//    }

    val calendarState = rememberSelectableCalendarState(
        initialMonth = YearMonth.now().plusYears(0),
        initialSelection = listOf(),
        initialSelectionMode = SelectionMode.Period,
    )

    var isCalendarVisible by remember { mutableStateOf(false) }
    var iconRotation by remember { mutableStateOf(0f) }
    val iconRotationState = remember { Animatable(iconRotation) }

    val iconImageVector = remember {
        if (isCalendarVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    }

    LaunchedEffect(isCalendarVisible) {
        val targetRotation = if (isCalendarVisible) 180f else 0f
        iconRotationState.animateTo(
            targetValue = targetRotation,
            animationSpec = tween(durationMillis = 300)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isCalendarVisible = !isCalendarVisible
                }
            ) {
                Text(
                    text = "Select Time Period",
                    style = TextStyle(color = Color.Black, fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = iconImageVector,
                    contentDescription = "Calendar",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .rotate(iconRotationState.value)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    println("excel")
                }
            ) {
                Text(
                    text = "Excel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Icon",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            AddClickableToRow(
                text = "PDF",
                icon = Icons.Default.Menu
            ) {
                println("PDF clicked!")

                val database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                val expensesDao = database.expensesDao()
                val categoriesDao = database.categorisDao()
                // Örnek kullanım

                 val coroutineScope = CoroutineScope(Dispatchers.IO)

                coroutineScope.launch {
                    val categoryId = categoriesDao.getAllCategories()
                    println("categoryid "+ categoryId)
                    val amount = 125f
                    val date = 225f
                    val newTaskId = expensesDao.insert(Expenses(description = "Örnek Görev", amount =amount, category_id = 1, date = date))

                    val result = expensesDao.getAllExpenses()

                    result.firstOrNull()?.let { firstExpense ->
                        val result2 = expensesDao.getExpenseWithCategory(firstExpense.get(0).id)
                        println("id " + result2?.expenses?.description + " category " + result2?.category?.name)

                        // result2'yi kullanarak devam edin
                    }
                }


                runBlocking {
                    //val newCategoryId = categoriesDao.insert(Categories(name = "Örnek Kategori"))


//
//                    val taskWithCategory = expensesDao.getExpenseWithCategory(newTaskId)
//
//                    val taskId = taskWithCategory?.expenses?.id ?: 0L
//                    val taskTitle = taskWithCategory?.expenses?.description ?: ""
//                    val taskContent = taskWithCategory?.expenses?.category_id ?: ""
//                    val categoryName = taskWithCategory?.category?.name ?: ""


                }




            }

        }

        AnimatedVisibility(
            visible = isCalendarVisible,
            enter = slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(durationMillis = 300)),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(durationMillis = 300))
        ) {
            SelectableCalendar(
                calendarState = calendarState,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
//        CategorizedLazyColumnTheme {
//            Surface(
//                color = MaterialTheme.colorScheme.background
//            ) {
//                CategorizedLazyColumn(
//                    categories = namesList
//                )
//            }
//        }
    }
}
@Composable
fun AddClickableToRow(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
    }
}
