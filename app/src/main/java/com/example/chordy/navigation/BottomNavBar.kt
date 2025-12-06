package com.example.chordy.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {
    //add home and progress
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Progress
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        //sets up the selection of the icons
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute ?: item.route)
                        launchSingleTop = true
                    }
                },
                //icons for my nav bar
                icon = { Icon(painterResource(item.icon), contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
