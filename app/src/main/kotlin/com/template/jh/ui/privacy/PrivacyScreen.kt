package com.template.jh.ui.privacy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import com.template.jh.R
import com.template.jh.ui.adaptive.rememberWindowSizeClass
import com.template.jh.ui.privacy.components.DataCollectionCard
import com.template.jh.ui.privacy.components.NetworkCard
import com.template.jh.ui.privacy.components.PermissionCard
import com.template.jh.ui.privacy.components.PrivacyButtons
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(
    onPrivacyAccepted: () -> Unit,
    onPrivacyRejected: () -> Unit,
    viewModel: PrivacyViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val windowSizeClass = rememberWindowSizeClass()

    LaunchedEffect(uiState.privacyAccepted) {
        if (uiState.privacyAccepted && !uiState.isLoading) {
            onPrivacyAccepted()
        }
    }

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
                title = { Text(stringResource(R.string.privacy_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                windowInsets = topBarInsets,
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        val contentModifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(innerPadding)
            .padding(innerPadding)

        if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
            // 平板/大屏布局：双列卡片 + 底部按钮，支持滚动
            Column(
                modifier = contentModifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PermissionCard(modifier = Modifier.weight(1f))
                    DataCollectionCard(modifier = Modifier.weight(1f))
                }
                NetworkCard(modifier = Modifier.fillMaxWidth())
                PrivacyButtons(
                    onReject = { viewModel.rejectPrivacy(onPrivacyRejected) },
                    onAccept = { viewModel.acceptPrivacy(onPrivacyAccepted) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        } else {
            // 手机布局：单列可滚动
            Column(
                modifier = contentModifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                PermissionCard(modifier = Modifier.fillMaxWidth())
                DataCollectionCard(modifier = Modifier.fillMaxWidth())
                NetworkCard(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.weight(1f))
                PrivacyButtons(
                    onReject = { viewModel.rejectPrivacy(onPrivacyRejected) },
                    onAccept = { viewModel.acceptPrivacy(onPrivacyAccepted) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}


