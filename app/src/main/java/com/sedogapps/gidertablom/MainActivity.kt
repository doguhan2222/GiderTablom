package com.sedogapps.gidertablom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sedogapps.gidertablom.screens.HomePage
import com.sedogapps.gidertablom.ui.theme.GiderTablomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiderTablomTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        bottomBar = {
                            BottomAppBar(
                                actions = {
                                    IconButton(onClick = { /* do something */ }) {
                                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                                    }
                                    IconButton(onClick = { /* do something */ }) {
                                        Icon(
                                            Icons.Filled.Edit,
                                            contentDescription = "Localized description",
                                        )
                                    }

                                },
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = { /* do something */ },
                                        containerColor = Color.Cyan,//BottomAppBarDefaults.bottomAppBarFabColor
                                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                                        shape = RoundedCornerShape(55.dp),
                                        contentColor = Color.Red

                                    ) {
                                        Icon(Icons.Filled.Add, "Localized description")
                                    }
                                }
                            )
                        },
                    ){it
                        HomePage()
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GiderTablomTheme {

    }
}