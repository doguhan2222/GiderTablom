package com.sedogapps.gidertablom.utilities

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sedogapps.gidertablom.screens.CalenderPage
import com.sedogapps.gidertablom.screens.HomePage
import com.sedogapps.gidertablom.screens.NewExpensesPage

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomePage(navController)
        }
        composable(Destinations.NewExpenses.route) {
            NewExpensesPage(navController)
        }
        composable(Destinations.Favourite.route) {
            CalenderPage(navController)
        }

    }
}