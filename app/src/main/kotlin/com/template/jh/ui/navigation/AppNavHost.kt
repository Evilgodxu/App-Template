package com.template.jh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.template.jh.ui.home.HomeScreen
import com.template.jh.ui.privacy.PrivacyScreen
import com.template.jh.ui.settings.SettingsScreen

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
                    navController.navigate(Settings) {
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Settings> {
            SettingsScreen(
                onNavigateBack = {
                    if (navController.currentBackStackEntry?.destination?.route == Settings.serializer().descriptor.serialName) {
                        navController.popBackStack()
                    }
                },
            )
        }
    }
}
