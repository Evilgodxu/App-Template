package com.template.jh.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import com.template.jh.R
import com.template.jh.ui.adaptive.rememberWindowSizeClass
import com.template.jh.ui.settings.components.LanguageSettingsCard
import com.template.jh.ui.settings.components.ThemeSettingsCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateBack: () -> Unit, viewModel: SettingsViewModel = koinViewModel(),) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val windowSizeClass = rememberWindowSizeClass()

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val topBarInsets = if (!windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)) {
        WindowInsets.statusBars
    } else {
        WindowInsets(0, 0, 0, 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
                windowInsets = topBarInsets,
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        val contentModifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .padding(16.dp)

        if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)) {
            // 横屏/平板布局：双列，支持滚动
            Row(
                modifier = contentModifier.verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                ThemeSettingsCard(
                    uiState = uiState,
                    onSetThemeMode = { viewModel.setThemeMode(it) },
                    modifier = Modifier.weight(1f),
                )
                LanguageSettingsCard(
                    uiState = uiState,
                    onSetLanguage = { viewModel.setLanguage(it) },
                    modifier = Modifier.weight(1f),
                )
            }
        } else {
            // 竖屏布局：单列可滚动
            Column(
                modifier = contentModifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                ThemeSettingsCard(
                    uiState = uiState,
                    onSetThemeMode = { viewModel.setThemeMode(it) },
                    modifier = Modifier.fillMaxWidth(),
                )
                LanguageSettingsCard(
                    uiState = uiState,
                    onSetLanguage = { viewModel.setLanguage(it) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}


