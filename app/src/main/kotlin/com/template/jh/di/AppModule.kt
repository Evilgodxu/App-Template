package com.template.jh.di

import com.template.jh.data.repository.UserPreferencesRepository
import com.template.jh.ui.home.HomeViewModel
import com.template.jh.ui.privacy.PrivacyViewModel
import com.template.jh.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserPreferencesRepository(androidContext()) }
    viewModel { PrivacyViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel(get()) }
}
