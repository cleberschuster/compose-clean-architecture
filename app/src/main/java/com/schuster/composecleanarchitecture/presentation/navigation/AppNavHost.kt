package com.schuster.composecleanarchitecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.schuster.composecleanarchitecture.presentation.feature.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object MainScreenRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenRoute
    ) {
        composable<MainScreenRoute> {
            MainScreen()
        }
    }
}


