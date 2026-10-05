package com.example.praktikum4.viewmodel

import com.example.praktikum4.model.Profile
import com.example.praktikum4.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel {
    // Private mutable state untuk form input
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // Private mutable state untuk daftar profile
    private val _profiles = MutableStateFlow<List<Profile>>(emptyList())
    val profiles: StateFlow<List<Profile>> = _profiles.asStateFlow()

    // Fungsi untuk mengupdate field di form
    fun updateName(value: String) {
        _uiState.update { it.copy(name = value, errorMessage = "") }
    }

    fun updateBio(value: String) {
        _uiState.update { it.copy(bio = value, errorMessage = "") }
    }

    fun updateEmail(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = "") }
    }

    fun updatePhone(value: String) {
        _uiState.update { it.copy(phone = value, errorMessage = "") }
    }

    fun updateLocation(value: String) {
        _uiState.update { it.copy(location = value, errorMessage = "") }
    }

    // Validasi dan simpan profile
    fun saveProfile(): Boolean {
        val currentState = _uiState.value

        // Validasi semua field harus terisi
        if (currentState.name.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Name tidak boleh kosong") }
            return false
        }

        if (currentState.bio.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Bio tidak boleh kosong") }
            return false
        }

        if (currentState.email.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Email tidak boleh kosong") }
            return false
        }

        // Validasi format email sederhana
        if (!currentState.email.contains("@")) {
            _uiState.update { it.copy(errorMessage = "Format email tidak valid") }
            return false
        }

        if (currentState.phone.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Phone tidak boleh kosong") }
            return false
        }

        if (currentState.location.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Location tidak boleh kosong") }
            return false
        }

        // Jika valid, tambahkan ke daftar profile
        val newProfile = Profile(
            name = currentState.name,
            bio = currentState.bio,
            email = currentState.email,
            phone = currentState.phone,
            location = currentState.location
        )

        _profiles.update { currentList ->
            currentList + newProfile
        }

        // Reset form setelah berhasil simpan
        resetForm()

        return true
    }

    // Reset form ke kondisi awal
    fun resetForm() {
        _uiState.value = ProfileUiState()
    }
}
