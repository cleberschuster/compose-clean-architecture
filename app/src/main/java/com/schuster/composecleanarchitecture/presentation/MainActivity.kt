package com.schuster.composecleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.schuster.composecleanarchitecture.presentation.navigation.AppNavHost
import com.schuster.composecleanarchitecture.presentation.ui.theme.AndroidCleanArchitectureTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AndroidCleanArchitectureTheme {
                AppNavHost()
            }
        }
    }
}

