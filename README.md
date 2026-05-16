# App Template

一个基于 Jetpack Compose 的 Android 应用模板项目，采用 MVVM + 单向数据流架构，开箱即用。

## 功能特性

- **自适应布局** — 根据设备屏幕尺寸自动切换紧凑/展开布局
- **多语言支持** — 支持简体中文与英文，应用内实时切换
- **主题切换** — 浅色 / 深色 / 跟随系统，运行时切换
- **隐私政策** — 内置隐私政策页面，首次启动引导用户确认
- **边缘到边缘** — 支持 Edge-to-Edge 显示，横屏自动隐藏系统栏

## 技术栈

| 类别 | 技术 |
|------|------|
| 语言 | Kotlin 2.3 |
| UI | Jetpack Compose + Material 3 |
| 架构 | MVVM + UDF（单向数据流） |
| 依赖注入 | Koin 4.2 |
| 导航 | Navigation 2.9 |
| 状态管理 | DataStore 1.2 + StateFlow |
| 异步处理 | Kotlin Coroutines + Flow |
| 序列化 | Kotlin Serialization |
| 自适应 | Material 3 Adaptive |

## 环境要求

- Android SDK：minSdk 32 / targetSdk 36
- JDK 21
- Kotlin 2.3.21
- AGP 9.2.1

## 项目结构

```
app/src/main/kotlin/com/template/jh/
├── MainActivity.kt
├── MyApplication.kt
├── data/repository/
│   └── UserPreferencesRepository.kt
├── di/
│   └── AppModule.kt
└── ui/
    ├── adaptive/
    │   └── WindowSizeClass.kt
    ├── home/
    │   ├── HomeScreen.kt
    │   ├── HomeViewModel.kt
    │   ├── HomeUiState.kt
    │   └── components/
    ├── localization/
    │   └── LanguageManager.kt
    ├── navigation/
    │   ├── AppNavHost.kt
    │   └── Screen.kt
    ├── privacy/
    │   ├── PrivacyScreen.kt
    │   ├── PrivacyViewModel.kt
    │   ├── PrivacyUiState.kt
    │   └── components/
    ├── settings/
    │   ├── SettingsScreen.kt
    │   ├── SettingsViewModel.kt
    │   ├── SettingsUiState.kt
    │   └── components/
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## 构建与运行

1. 克隆仓库

```bash
git clone https://github.com/Evilgodxu/App-Template.git
```

2. 使用 Android Studio 打开项目

3. 同步 Gradle 后直接运行

## 许可证

[MIT License](LICENSE)
