package com.myapplication.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val themeMode = userPreferencesRepository.themeMode.first()
            val language = userPreferencesRepository.language.first()
            _uiState.value = SettingsUiState(
                isLoading = false,
                themeMode = themeMode,
                language = language,
            )
        }
    }

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            userPreferencesRepository.setThemeMode(mode)
            _uiState.value = _uiState.value.copy(themeMode = mode)
        }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch {
            userPreferencesRepository.setLanguage(language)
            _uiState.value = _uiState.value.copy(language = language)
        }
    }
}
