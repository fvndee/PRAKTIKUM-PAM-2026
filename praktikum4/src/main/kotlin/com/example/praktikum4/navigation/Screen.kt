package com.example.praktikum4.navigation

sealed class Screen {
    object ProfileList : Screen()
    object AddProfile : Screen()
}
