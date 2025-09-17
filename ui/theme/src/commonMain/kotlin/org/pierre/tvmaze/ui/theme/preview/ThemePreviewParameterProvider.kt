package org.pierre.tvmaze.ui.theme.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.tvmaze.core.preferences.ThemePreference

class ThemePreviewParameterProvider : PreviewParameterProvider<ThemePreference> {
    override val values: Sequence<ThemePreference> = sequenceOf(
        ThemePreference.LIGHT,
        ThemePreference.DARK,
    )
}
