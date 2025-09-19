package org.pierre.tvmaze.ui.theme.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.core.model.theme.Theme

class AllThemePreferencesPreviewParameterProvider: PreviewParameterProvider<Theme> {
    override val values: Sequence<Theme> = Theme.entries.asSequence()
}
