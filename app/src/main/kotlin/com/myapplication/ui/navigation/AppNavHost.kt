package com.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myapplication.ui.home.HomeScreen
import com.myapplication.ui.privacy.PrivacyScreen
import com.myapplication.ui.settings.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
    onExitApp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Privacy> {
            PrivacyScreen(
                onPrivacyAccepted = {
                    navController.navigate(Home) {
                        popUpTo<Privacy> { inclusive = true }
                    }
                },
                onPrivacyRejected = onExitApp,
            )
        }

        composable<Home> {
            HomeScreen(
                onNavigateToSettings = {
                    navController.navigate(Settings)
                },
            )
        }

        composable<Settings> {
            SettingsScreen(
                onNavigateBack = {
                    if (navController.currentBackStackEntry?.destination?.route == Settings::class.qualifiedName) {
                        navController.popBackStack()
                    }
                },
            )
        }
    }
}
