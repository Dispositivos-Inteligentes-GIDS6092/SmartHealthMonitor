package mx.utng.smarthealthmonitor.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = SHPrimary,
    onPrimary = SHOnPrimary,
    primaryContainer = SHPrimaryContainer,
    onPrimaryContainer = SHOnPrimaryContainer,

    secondary = SHSecondary,
    onSecondary = SHOnSecondary,
    secondaryContainer = SHSecondaryContainer,
    onSecondaryContainer = SHOnSecondaryContainer,

    tertiary = SHTertiary,
    onTertiary = SHOnTertiary,

    error = SHError,
    onError = SHOnError,
    errorContainer = SHErrorContainer,
    onErrorContainer = SHOnErrorContainer,

    background = SHBackground,
    onBackground = SHOnBackground,
    surface = SHSurface,
    onSurface = SHOnSurface,

    surfaceVariant = SHSurfaceVariant,
    onSurfaceVariant = SHOnSurfaceVariant,
    outline = SHOutline,
)

private val DarkColorScheme = darkColorScheme(
    primary = SHPrimaryDark,
    onPrimary = SHOnPrimaryDark,
    primaryContainer = SHPrimaryContainerDark,

    background = SHBackgroundDark,
    onBackground = SHOnBackgroundDark,
    surface = SHSurfaceDark,
    onSurface = SHOnSurfaceDark,

    error = SHErrorDark,
    onError = SHOnErrorDark,
)

@Composable
fun SmartHealthMonitorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}