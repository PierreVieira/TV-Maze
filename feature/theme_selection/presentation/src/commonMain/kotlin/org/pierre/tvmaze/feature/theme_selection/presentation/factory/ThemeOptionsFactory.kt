package org.pierre.tvmaze.feature.theme_selection.presentation.factory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.LightMode
import org.pierre.core.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.presentation.model.ThemeSelectionModel
import tvmaze.feature.theme_selection.presentation.generated.resources.Res
import tvmaze.feature.theme_selection.presentation.generated.resources.dark_description
import tvmaze.feature.theme_selection.presentation.generated.resources.dark_title
import tvmaze.feature.theme_selection.presentation.generated.resources.light_description
import tvmaze.feature.theme_selection.presentation.generated.resources.light_title
import tvmaze.feature.theme_selection.presentation.generated.resources.system_description
import tvmaze.feature.theme_selection.presentation.generated.resources.system_title

object ThemeOptionsFactory {
    val themeOptions: List<ThemeSelectionModel> = listOf(
        ThemeSelectionModel(
            title = Res.string.light_title,
            subtitle = Res.string.light_description,
            icon = Icons.Filled.LightMode,
            preference = Theme.LIGHT,
        ),
        ThemeSelectionModel(
            title = Res.string.dark_title,
            subtitle = Res.string.dark_description,
            icon = Icons.Filled.DarkMode,
            preference = Theme.DARK,
        ),
        ThemeSelectionModel(
            title = Res.string.system_title,
            subtitle = Res.string.system_description,
            icon = Icons.Filled.Devices,
            preference = Theme.SYSTEM,
        ),
    )
}
