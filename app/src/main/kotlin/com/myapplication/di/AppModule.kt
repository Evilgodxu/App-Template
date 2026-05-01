package com.myapplication.di

import com.myapplication.data.repository.UserPreferencesRepository
import com.myapplication.ui.home.HomeViewModel
import com.myapplication.ui.privacy.PrivacyViewModel
import com.myapplication.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserPreferencesRepository(androidContext()) }
    viewModel { PrivacyViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel(get()) }
}
