package com.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.myapplication.data.repository.UserPreferencesRepository
import com.myapplication.ui.adaptive.ProvideWindowSizeClass
import com.myapplication.ui.localization.LanguageManager
import com.myapplication.ui.localization.ProvideLocalizedContext
import com.myapplication.ui.navigation.AppNavHost
import com.myapplication.ui.navigation.Home
import com.myapplication.ui.navigation.Privacy
import com.myapplication.ui.theme.MyApplicationTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val userPreferencesRepository: UserPreferencesRepository by inject()
    private lateinit var languageManager: LanguageManager
    private lateinit var windowInsetsController: WindowInsetsControllerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupSystemBars()

        languageManager = LanguageManager(this, userPreferencesRepository)

        setContent {
            val navController = rememberNavController()
            val themeMode by userPreferencesRepository.themeMode.collectAsStateWithLifecycle(initialValue = "system")
            val privacyAccepted by userPreferencesRepository.privacyAccepted.collectAsStateWithLifecycle(initialValue = false)

            val darkTheme = when (themeMode) {
                "light" -> false
                "dark" -> true
                else -> androidx.compose.foundation.isSystemInDarkTheme()
            }

            var isInitialLaunch by rememberSaveable { mutableStateOf(true) }
            val startDestination = if (isInitialLaunch) {
                isInitialLaunch = false
                if (privacyAccepted) Home else Privacy
            } else {
                null
            }

            ProvideLocalizedContext(languageManager) {
                ProvideWindowSizeClass {
                    MyApplicationTheme(darkTheme = darkTheme, dynamicColor = false) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            if (startDestination != null) {
                                AppNavHost(
                                    navController = navController,
                                    startDestination = startDestination,
                                    onExitApp = { finish() },
                                )
                            } else {
                                AppNavHost(
                                    navController = navController,
                                    startDestination = Home,
                                    onExitApp = { finish() },
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSystemBars() {
        windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        updateSystemBarsVisibility()
    }

    private fun updateSystemBarsVisibility() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        } else {
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateSystemBarsVisibility()
    }
}
