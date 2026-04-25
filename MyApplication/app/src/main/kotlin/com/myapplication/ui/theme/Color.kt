package com.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================
// 浅色主题 - 柔和白 + 浅灰
// ============================================

// 主色调 - 用于主要按钮、选中状态、关键操作
val md_theme_light_primary = Color(0xFF5A5A5A) // 主色：中灰色
val md_theme_light_onPrimary = Color(0xFFFFFFFF) // 主色上的文字：白色
val md_theme_light_primaryContainer = Color(0xFFE8E8E8) // 主色容器：浅灰背景
val md_theme_light_onPrimaryContainer = Color(0xFF2A2A2A) // 主色容器上的文字：深灰

// 次要色 - 用于次要按钮、辅助操作
val md_theme_light_secondary = Color(0xFF6B6B6B) // 次要色：稍浅的灰
val md_theme_light_onSecondary = Color(0xFFFFFFFF) // 次要色上的文字：白色
val md_theme_light_secondaryContainer = Color(0xFFF0F0F0) // 次要色容器：更浅的灰
val md_theme_light_onSecondaryContainer = Color(0xFF3A3A3A) // 次要色容器上的文字

// 第三色 - 用于强调、标签、特殊标记
val md_theme_light_tertiary = Color(0xFF7A7A7A) // 第三色：浅灰
val md_theme_light_onTertiary = Color(0xFFFFFFFF) // 第三色上的文字
val md_theme_light_tertiaryContainer = Color(0xFFF5F5F5) // 第三色容器：最浅的灰
val md_theme_light_onTertiaryContainer = Color(0xFF4A4A4A) // 第三色容器上的文字

// 错误色 - 用于错误提示、删除操作
val md_theme_light_error = Color(0xFFB3261E) // 错误色：红色
val md_theme_light_onError = Color(0xFFFFFFFF) // 错误色上的文字
val md_theme_light_errorContainer = Color(0xFFF9DEDC) // 错误容器：浅红背景
val md_theme_light_onErrorContainer = Color(0xFF410E0B) // 错误容器上的文字

// 轮廓线 - 用于边框、分割线
val md_theme_light_outline = Color(0xFFBDBDBD) // 轮廓线：灰色
val md_theme_light_outlineVariant = Color(0xFFE0E0E0) // 轮廓线变体：更浅的灰

// 背景色 - 用于页面背景
val md_theme_light_background = Color(0xFFFAFAFA) // 页面背景：柔和白
val md_theme_light_onBackground = Color(0xFF1C1C1C) // 背景上的文字：近黑色

// 表面色 - 用于卡片、对话框、底部栏等表面元素
val md_theme_light_surface = Color(0xFFFFFFFF) // 表面：纯白色（卡片背景）
val md_theme_light_onSurface = Color(0xFF1C1C1C) // 表面上的文字
val md_theme_light_surfaceVariant = Color(0xFFF5F5F5) // 表面变体：浅灰（选项背景）
val md_theme_light_onSurfaceVariant = Color(0xFF4A4A4A) // 表面变体上的文字

// 反色 - 用于在深色背景上显示浅色元素
val md_theme_light_inverseSurface = Color(0xFF2C2C2C) // 反色表面：深灰
val md_theme_light_inverseOnSurface = Color(0xFFF5F5F5) // 反色表面上的文字
val md_theme_light_inversePrimary = Color(0xFF9A9A9A) // 反色主色

// 表面色调 - 用于给表面元素添加主色调的色调
val md_theme_light_surfaceTint = Color(0xFF5A5A5A) // 表面色调

// 遮罩 - 用于弹窗背景遮罩
val md_theme_light_scrim = Color(0xFF000000) // 遮罩：黑色半透明

// ============================================
// 深色主题 - 深灰 + 灰紫色
// ============================================

// 主色调 - 用于主要按钮、选中状态、关键操作
val md_theme_dark_primary = Color(0xFFB8B8B8) // 主色：浅灰
val md_theme_dark_onPrimary = Color(0xFF2C2C2C) // 主色上的文字：深灰
val md_theme_dark_primaryContainer = Color(0xFF4A4A5A) // 主色容器：灰紫色
val md_theme_dark_onPrimaryContainer = Color(0xFFE8E8F0) // 主色容器上的文字：浅灰白

// 次要色 - 用于次要按钮、辅助操作
val md_theme_dark_secondary = Color(0xFFA8A8B8) // 次要色：灰紫色
val md_theme_dark_onSecondary = Color(0xFF2A2A3A) // 次要色上的文字
val md_theme_dark_secondaryContainer = Color(0xFF3A3A4A) // 次要色容器：深灰紫
val md_theme_dark_onSecondaryContainer = Color(0xFFD8D8E8) // 次要色容器上的文字

// 第三色 - 用于强调、标签、特殊标记
val md_theme_dark_tertiary = Color(0xFFC8C8D8) // 第三色：浅灰紫
val md_theme_dark_onTertiary = Color(0xFF2E2E3E) // 第三色上的文字
val md_theme_dark_tertiaryContainer = Color(0xFF4A4A5A) // 第三色容器
val md_theme_dark_onTertiaryContainer = Color(0xFFE8E8F8) // 第三色容器上的文字

// 错误色 - 用于错误提示、删除操作
val md_theme_dark_error = Color(0xFFF2B8B5) // 错误色：浅红色
val md_theme_dark_onError = Color(0xFF601410) // 错误色上的文字
val md_theme_dark_errorContainer = Color(0xFF8C1D18) // 错误容器：深红色
val md_theme_dark_onErrorContainer = Color(0xFFF9DEDC) // 错误容器上的文字

// 轮廓线 - 用于边框、分割线
val md_theme_dark_outline = Color(0xFF6A6A7A) // 轮廓线：灰紫色
val md_theme_dark_outlineVariant = Color(0xFF3A3A48) // 轮廓线变体：深灰紫

// 背景色 - 用于页面背景
val md_theme_dark_background = Color(0xFF1A1A20) // 页面背景：深灰黑
val md_theme_dark_onBackground = Color(0xFFE8E8EC) // 背景上的文字：浅灰白

// 表面色 - 用于卡片、对话框、底部栏等表面元素
val md_theme_dark_surface = Color(0xFF222228) // 表面：深灰（卡片背景）
val md_theme_dark_onSurface = Color(0xFFE8E8EC) // 表面上的文字
val md_theme_dark_surfaceVariant = Color(0xFF2E2E38) // 表面变体：灰紫色（选项背景）
val md_theme_dark_onSurfaceVariant = Color(0xFFC8C8D0) // 表面变体上的文字

// 反色 - 用于在浅色背景上显示深色元素
val md_theme_dark_inverseSurface = Color(0xFFE8E8EC) // 反色表面：浅灰白
val md_theme_dark_inverseOnSurface = Color(0xFF2C2C32) // 反色表面上的文字
val md_theme_dark_inversePrimary = Color(0xFF787888) // 反色主色

// 表面色调 - 用于给表面元素添加主色调的色调
val md_theme_dark_surfaceTint = Color(0xFFB8B8C8) // 表面色调：灰紫色

// 遮罩 - 用于弹窗背景遮罩
val md_theme_dark_scrim = Color(0xFF000000) // 遮罩：黑色半透明
