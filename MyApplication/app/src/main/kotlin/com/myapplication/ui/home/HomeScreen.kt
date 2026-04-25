package com.myapplication.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.ui.adaptive.rememberWindowSizeInfo
import com.myapplication.ui.localization.stringResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToSettings: () -> Unit, viewModel: HomeViewModel = koinViewModel()) {
    val windowSizeInfo = rememberWindowSizeInfo()
    val topBarInsets = if (windowSizeInfo.isCompact) {
        WindowInsets.statusBars
    } else {
        WindowInsets(0, 0, 0, 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_title)) },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings_title),
                        )
                    }
                },
                windowInsets = topBarInsets,
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.home_welcome),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(32.dp),
            )
        }
    }
}
