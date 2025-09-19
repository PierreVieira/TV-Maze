package org.pierre.tvmaze.switch_android_color_scheme.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.tvmaze.switch_android_color_scheme.presentation.R
import org.pierre.tvmaze.switch_android_color_scheme.presentation.model.AndroidColorSchemeUiEvent
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton
import org.pierre.tvmaze.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidColorSchemeSwitchComponent(
    isChecked: Boolean,
    onEvent: (AndroidColorSchemeUiEvent) -> Unit,
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.android_dynamic_colors))
                CommonIconButton(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Information",
                    onClick = { onEvent(AndroidColorSchemeUiEvent.OnInformationIconClick) },
                )
            }
        },
        actions = {
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    onEvent(AndroidColorSchemeUiEvent.OnIsDynamicColorsEnabledChange(it))
                },
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun AndroidColorSchemeSwitchComponentPreview(
    @PreviewParameter(AndroidColorSchemeSwitchComponentPreviewParameterProvider::class)
    isOn: Boolean,
) {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            AndroidColorSchemeSwitchComponent(isOn) {}
        }
    }
}

private class AndroidColorSchemeSwitchComponentPreviewParameterProvider :
    PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)

}
