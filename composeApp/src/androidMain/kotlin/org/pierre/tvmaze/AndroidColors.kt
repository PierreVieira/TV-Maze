package org.pierre.tvmaze

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun getAndroidSpecificColorScheme(isAppInDarkTheme: Boolean): ColorScheme? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (isAppInDarkTheme) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        null
    }
