package com.example.chordy.navigation

import androidx.navigation.compose.composable
import com.example.chordy.ui.theme.HomeScreen
import com.example.chordy.ui.theme.ProgressScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chordy.ui.theme.SettingsScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(navController: NavHostController) {
    //structure for my screens
    Scaffold(bottomBar = { BottomNavBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            //routes for my different screens
            composable("home") { HomeScreen(onNavigateToSettings = { navController.navigate("settings") } ) }
            composable("progress") { ProgressScreen() }
            composable("settings") {
                SettingsScreen(onBack = { navController.popBackStack() })
            }


        }
    }
}
