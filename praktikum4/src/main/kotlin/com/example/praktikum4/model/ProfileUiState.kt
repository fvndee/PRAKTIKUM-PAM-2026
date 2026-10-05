package com.example.praktikum4.model

data class ProfileUiState(
    val name: String = "",
    val bio: String = "",
    val email: String = "",
    val phone: String = "",
    val location: String = "",
    val errorMessage: String = ""
)
