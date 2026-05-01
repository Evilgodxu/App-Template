package com.myapplication.ui.localization

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.myapplication.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale

/**
 * 语言管理器，负责根据用户偏好设置提供本地化 Context
 */
class LanguageManager(
    private val context: Context,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    val languageFlow: Flow<String> = userPreferencesRepository.language

    val localeFlow: Flow<Locale> = languageFlow.map { languageCode ->
        resolveLocale(languageCode)
    }

    /**
     * 根据语言代码解析对应的 Locale
     */
    fun resolveLocale(languageCode: String): Locale {
        return when (languageCode) {
            "zh" -> Locale.SIMPLIFIED_CHINESE
            "en" -> Locale.ENGLISH
            else -> Locale.getDefault()
        }
    }

    /**
     * 根据指定 Locale 创建本地化的 Context
     */
    fun createLocalizedContext(locale: Locale): Context {
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}

/**
 * 为 Composable 树提供本地化 Context
 * 通过替换 LocalContext，使所有子组件自动使用正确的语言资源
 */
@Composable
fun ProvideLocalizedContext(
    languageManager: LanguageManager,
    content: @Composable () -> Unit,
) {
    val locale by languageManager.localeFlow.collectAsState(
        initial = Locale.getDefault(),
    )
    val localizedContext = languageManager.createLocalizedContext(locale)

    CompositionLocalProvider(LocalContext provides localizedContext) {
        content()
    }
}
