package com.example.chordy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.chordy.navigation.AppNavGraph
import com.example.chordy.ui.theme.ChordyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChordyTheme {
                //nav controller for my screens
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
