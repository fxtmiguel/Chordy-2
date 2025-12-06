package com.example.chordy.navigation

import androidx.annotation.DrawableRes
import com.example.chordy.R

//sets up my items with their route and title
sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    //creates my objects for my items
    data object Home : BottomNavItem("home", "Home", R.drawable.ic_home)
    data object Progress : BottomNavItem("progress", "Progress", R.drawable.ic_progress)
}
