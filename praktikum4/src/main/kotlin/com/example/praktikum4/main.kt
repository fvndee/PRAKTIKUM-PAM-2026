package com.example.praktikum4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.praktikum4.navigation.Screen
import com.example.praktikum4.ui.screens.ProfileFormScreen
import com.example.praktikum4.ui.screens.ProfileListScreen
import com.example.praktikum4.viewmodel.ProfileViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Profile App - MVVM Pattern",
        state = rememberWindowState(width = 900.dp, height = 800.dp),
    ) {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFF5F5F5),
            ) {
                ProfileApp()
            }
        }
    }
}

@Composable
fun ProfileApp() {
    // Inisialisasi ViewModel - single instance untuk seluruh aplikasi
    val viewModel = remember { ProfileViewModel() }
    
    // State untuk navigasi
    var currentScreen by remember { mutableStateOf<Screen>(Screen.ProfileList) }
    
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
    ) {
        val isCompact = maxWidth < 600.dp
        
        // Navigasi antar screen
        when (currentScreen) {
            Screen.ProfileList -> {
                ProfileListScreen(
                    viewModel = viewModel,
                    isCompact = isCompact,
                    onAddProfileClick = { currentScreen = Screen.AddProfile },
                    modifier = Modifier.fillMaxSize()
                )
            }
            Screen.AddProfile -> {
                ProfileFormScreen(
                    viewModel = viewModel,
                    onBackClick = { currentScreen = Screen.ProfileList },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
