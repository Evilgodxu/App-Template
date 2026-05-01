package com.myapplication.ui.adaptive

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.window.core.layout.WindowSizeClass

enum class DeviceType {
    COMPACT, // 手机竖屏
    MEDIUM, // 手机横屏/小平板
    EXPANDED, // 平板/大屏设备
}

class WindowSizeInfo(
    val windowSizeClass: WindowSizeClass,
    val deviceType: DeviceType,
) {
    val isCompact: Boolean
        get() = deviceType == DeviceType.COMPACT

    val isMedium: Boolean
        get() = deviceType == DeviceType.MEDIUM

    val isExpanded: Boolean
        get() = deviceType == DeviceType.EXPANDED

    val isTablet: Boolean
        get() = deviceType == DeviceType.MEDIUM || deviceType == DeviceType.EXPANDED
}

val LocalWindowSizeInfo = compositionLocalOf<WindowSizeInfo> {
    error("WindowSizeInfo not provided")
}

@Composable
fun ProvideWindowSizeInfo(content: @Composable () -> Unit) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass

    val deviceType = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) ->
            if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
                DeviceType.EXPANDED
            } else {
                DeviceType.MEDIUM
            }
        else -> DeviceType.COMPACT
    }

    val windowSizeInfo = WindowSizeInfo(
        windowSizeClass = windowSizeClass,
        deviceType = deviceType,
    )

    CompositionLocalProvider(LocalWindowSizeInfo provides windowSizeInfo) {
        content()
    }
}

@Composable
fun rememberWindowSizeInfo(): WindowSizeInfo {
    return LocalWindowSizeInfo.current
}
