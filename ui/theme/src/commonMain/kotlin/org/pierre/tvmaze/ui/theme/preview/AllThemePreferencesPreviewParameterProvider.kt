package org.pierre.tvmaze.ui.theme.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.tvmaze.core.preferences.ThemePreference

class AllThemePreferencesPreviewParameterProvider: PreviewParameterProvider<ThemePreference> {
    override val values: Sequence<ThemePreference> = ThemePreference.entries.asSequence()
}
