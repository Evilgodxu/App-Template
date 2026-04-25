# MyApplication

一个基于现代 Android 技术栈构建的应用模板，采用 MVVM + Clean Architecture 架构，支持自适应布局和国际化。

## 技术栈

| 类别 | 技术 |
|------|------|
| **语言** | Kotlin 2.3.21 |
| **UI 框架** | Jetpack Compose + Material3 |
| **架构模式** | MVVM + UDF + Clean Architecture |
| **依赖注入** | Koin 4.2.1 |
| **导航框架** | Navigation 2.9.8 |
| **状态管理** | DataStore + StateFlow |
| **数据存储** | DataStore 1.2.1 + Room |
| **异步处理** | Kotlin Coroutines + Flow |
| **自适应布局** | Material3 Adaptive 1.2.0 |
| **后台任务** | WorkManager |

## 项目结构

```
app/src/main/kotlin/com/myapplication/
├── MainActivity.kt                 # 应用入口
├── MyApplication.kt                # Application 类
├── data/
│   └── repository/
│       └── UserPreferencesRepository.kt  # 用户偏好数据仓库
├── di/
│   └── AppModule.kt                # Koin 依赖注入配置
├── ui/
│   ├── adaptive/
│   │   └── WindowSizeClass.kt      # 自适应布局工具
│   ├── home/
│   │   ├── HomeScreen.kt           # 首页
│   │   ├── HomeUiState.kt          # 首页状态
│   │   └── HomeViewModel.kt        # 首页视图模型
│   ├── localization/
│   │   └── LanguageManager.kt      # 语言管理器
│   ├── navigation/
│   │   ├── AppNavHost.kt           # 导航宿主
│   │   └── Screen.kt               # 路由定义
│   ├── privacy/
│   │   ├── PrivacyScreen.kt        # 隐私政策页
│   │   ├── PrivacyUiState.kt       # 隐私页状态
│   │   └── PrivacyViewModel.kt     # 隐私页视图模型
│   ├── settings/
│   │   ├── SettingsScreen.kt       # 设置页
│   │   ├── SettingsUiState.kt      # 设置页状态
│   │   └── SettingsViewModel.kt    # 设置页视图模型
│   └── theme/
│       ├── Color.kt                # 颜色定义
│       ├── Theme.kt                # 主题配置
│       └── Type.kt                 # 字体配置
└── res/
    ├── values/
    │   └── strings.xml             # 中文字符串
    └── values-en/
        └── strings.xml             # 英文字符串
```

## 功能特性

### 已实现功能

- **隐私政策** - 首次启动展示隐私政策，用户需同意后才能使用
- **主题切换** - 支持亮色/暗色/跟随系统三种模式
- **多语言** - 支持中文/英文/跟随系统，实时切换无闪烁
- **自适应布局** - 根据屏幕尺寸自动调整布局（手机/平板/横竖屏）
- **状态栏控制** - 横屏自动隐藏状态栏，竖屏显示
- **配置变更处理** - 旋转屏幕不丢失状态

### 页面说明

| 页面 | 功能描述 |
|------|----------|
| **隐私政策页** | 展示应用隐私政策，用户需点击"同意"才能进入主页面 |
| **首页** | 应用主界面，包含设置入口按钮 |
| **设置页** | 主题设置、语言设置功能 |

## 快速开始

### 环境要求

- Android Studio Ladybug (2024.2.1) 或更高版本
- JDK 21
- Android SDK 36

### 构建运行

```bash
# 克隆项目后构建调试版本
./gradlew assembleDebug

# 安装到设备
./gradlew installDebug

# 运行静态代码检查
./gradlew lint
```

### 生成发布版本

```bash
./gradlew assembleRelease
```

发布版本 APK 位于 `app/build/outputs/apk/release/`

## 架构说明

### MVVM 架构

```
UI Layer (Compose Screen)
    ↓
ViewModel (StateFlow)
    ↓
Repository (DataStore/Room)
    ↓
Data Source
```

### 状态管理

- **UI 状态** - 使用 `StateFlow` 管理，通过 `collectAsStateWithLifecycle()` 收集
- **用户偏好** - 使用 `DataStore` 持久化存储
- **语言设置** - 使用 `CompositionLocalProvider` 实现 Compose 级语言切换

### 自适应布局

根据屏幕宽度自动分类设备类型：

| 设备类型 | 宽度范围 | 布局特点 |
|----------|----------|----------|
| COMPACT | < 600dp | 手机竖屏，单列布局 |
| MEDIUM | 600-840dp | 手机横屏/小平板，可双列布局 |
| EXPANDED | > 840dp | 平板/大屏设备，多列布局 |

## 依赖版本

```toml
[versions]
agp = "9.2.0"
kotlin = "2.3.21"
composeBom = "2026.04.01"
navigation = "2.9.8"
koin = "4.2.1"
datastore = "1.2.1"
adaptive = "1.2.0"
```

完整依赖列表见 [gradle/libs.versions.toml](gradle/libs.versions.toml)

## 代码规范

- 使用 **中文注释**
- 遵循 **Kotlin 编码规范**
- 使用 **版本目录** 管理依赖
- 运行 `./gradlew lint` 进行静态代码检查
