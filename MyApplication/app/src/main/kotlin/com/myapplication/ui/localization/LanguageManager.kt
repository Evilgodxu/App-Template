package com.myapplication.ui.localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.myapplication.data.repository.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale

class LanguageManager(private val context: Context) {
    private val userPreferencesRepository = UserPreferencesRepository(context)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    var currentLocale by mutableStateOf(getInitialLocale())
        private set

    init {
        scope.launch {
            userPreferencesRepository.language.collect { languageCode ->
                currentLocale = resolveLocale(languageCode)
            }
        }
    }

    private fun getInitialLocale(): Locale {
        val languageCode = runBlocking { userPreferencesRepository.language.first() }
        return resolveLocale(languageCode)
    }

    fun resolveLocale(languageCode: String): Locale {
        return when (languageCode) {
            "zh" -> Locale.SIMPLIFIED_CHINESE
            "en" -> Locale.ENGLISH
            else -> Locale.getDefault()
        }
    }

    fun getLocalizedResources(context: Context, locale: Locale): Resources {
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        val localizedContext = context.createConfigurationContext(config)
        return localizedContext.resources
    }
}

val LocalLanguageManager = compositionLocalOf<LanguageManager> {
    error("LanguageManager not provided")
}

@Composable
fun ProvideLanguageManager(languageManager: LanguageManager, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLanguageManager provides languageManager) {
        content()
    }
}

@Composable
fun stringResource(id: Int): String {
    val languageManager = LocalLanguageManager.current
    val context = LocalContext.current
    val resources = languageManager.getLocalizedResources(context, languageManager.currentLocale)
    return resources.getString(id)
}

@Composable
fun stringResource(id: Int, vararg formatArgs: Any): String {
    val languageManager = LocalLanguageManager.current
    val context = LocalContext.current
    val resources = languageManager.getLocalizedResources(context, languageManager.currentLocale)
    return resources.getString(id, *formatArgs)
}
