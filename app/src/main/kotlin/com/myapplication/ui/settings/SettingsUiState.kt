package com.myapplication.ui.settings

data class SettingsUiState(
    val isLoading: Boolean = true,
    val themeMode: String = "system",
    val language: String = "system",
)
