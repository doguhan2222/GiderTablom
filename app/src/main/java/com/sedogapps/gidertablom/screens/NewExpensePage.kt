package com.sedogapps.gidertablom.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sedogapps.gidertablom.room.entities.Expenses
import com.sedogapps.gidertablom.viewmodel.NewExpensePageViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.YearMonth


@Composable
fun NewExpensesPage(navController: NavHostController,viewModel: NewExpensePageViewModel = hiltViewModel()) {
    //val viewModel: NewExpensePageViewModel = hiltViewModel()
    var context = LocalContext.current
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val calendarState = rememberSelectableCalendarState(
        initialMonth = YearMonth.now().plusYears(0),
        initialSelection = listOf(),
        initialSelectionMode = SelectionMode.Period,
    )
    var isCalendarVisible by remember { mutableStateOf(false) }




    var categoryListForMenu by remember { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(viewModel) {
        viewModel.getCategories()
    }
    val categories by viewModel.categoryList.observeAsState(emptyList())
    val isSuccessful by viewModel.isSuccesful.observeAsState(false)

    categoryListForMenu = categories.map { it.name }

// Herhangi bir zaman aralığında isSuccessful değeri değiştiğinde tetiklenen blok
    LaunchedEffect(isSuccessful) {
        if (isSuccessful) {
            Toast.makeText(context, "İşlem başarılı!", Toast.LENGTH_SHORT).show()

            viewModel.setIsSuccessful(false)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Add new expense",
            style = TextStyle(color =
                Color.Black,
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Enter the details of your expense   to help you \ntrack your spending.",
            style = TextStyle(color =
            Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = amount ,
            onValueChange = { newValue ->
                amount = newValue
            },
            label = { Text("Enter Amount") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Money Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Description",
            style = TextStyle(color =
            Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W600
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = description ,
            onValueChange = { newValue ->
                description = newValue
            },
            label = { Text("Enter Description") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Description Icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Category",
            style = TextStyle(color =
            Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W600
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        CategoryButton(
            text = "Select Item",
            onItemClick = { selectedItem ->
                // Seçilen öğe ile ilgili işlemler burada yapılabilir
                // Örneğin, seçilen öğeyi yazdıralım
                println("Selected Item: $selectedItem")
            },
            items = categoryListForMenu
        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { isCalendarVisible = !isCalendarVisible }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Calendar Icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Open Calendar")
            }
        }

        if (isCalendarVisible) {
            SelectableCalendar(
                calendarState = calendarState,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        
        Button(onClick = {
            var expenseForsave:Expenses = Expenses(
                date = 123f,
                category_id = 2,
                amount = 315f,
                description = "dfdsfsdsfd",
            );
            viewModel.saveNewExpense(expenseForsave)



        }) {
            Text(text = "kaydet")
        }



    }
}



@Composable
fun CategoryButton(
    text: String,
    onItemClick: (String) -> Unit,
    items: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Sol taraftaki icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop Down Icon",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Orta kısımdaki metin
            Text(
                text = if (selectedItem.isEmpty()) text else selectedItem,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Sağ taraftaki icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop Down Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = { Text(text = item) },

                onClick = {
                    selectedItem = item
                    expanded = false
                    println(item)
                }
            )
        }

    }
}
