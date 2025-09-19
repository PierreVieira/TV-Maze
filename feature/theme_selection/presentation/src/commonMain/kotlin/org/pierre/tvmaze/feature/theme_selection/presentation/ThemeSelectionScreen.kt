package org.pierre.tvmaze.feature.theme_selection.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.pierre.core.theme.Theme
import org.pierre.tvmaze.feature.theme_selection.presentation.component.ThemeOptionCard
import org.pierre.tvmaze.feature.theme_selection.presentation.factory.ThemeOptionsFactory
import org.pierre.tvmaze.feature.theme_selection.presentation.model.ThemeSelectionModel
import org.pierre.tvmaze.ui.theme.preview.AllThemePreferencesPreviewParameterProvider
import org.pierre.tvmaze.ui.theme.preview.PreviewTheme

@Composable
fun ThemeSelectionScreen(
    options: List<ThemeSelectionModel>,
    setTheme: (Theme) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        val isWide = maxWidth > 600.dp
        val commonModifier = Modifier.align(Alignment.Center)
        if (isWide) {
            Row(
                modifier = commonModifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                options.forEach {
                    ThemeOptionCard(
                        model = it,
                        onClick = setTheme,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        } else {
            LazyColumn(modifier = commonModifier, verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
                items(options) {
                    ThemeOptionCard(
                        model = it,
                        onClick = setTheme,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ThemeSelectionScreenPreview(
    @PreviewParameter(AllThemePreferencesPreviewParameterProvider::class)
    currentTheme: Theme,
) {
    PreviewTheme(currentTheme) {
        ThemeSelectionScreen(options = ThemeOptionsFactory.themeOptions, setTheme = {})
    }
}
