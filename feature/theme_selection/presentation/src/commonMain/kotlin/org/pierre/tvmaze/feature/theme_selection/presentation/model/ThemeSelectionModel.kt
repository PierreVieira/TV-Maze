package org.pierre.tvmaze.feature.theme_selection.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.pierre.core.model.theme.Theme

data class ThemeSelectionModel(
    val title: StringResource,
    val subtitle: StringResource,
    val icon: ImageVector,
    val preference: Theme,
)
