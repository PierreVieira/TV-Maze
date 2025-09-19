package org.pierre.tvmaze.material_you.presentation.component

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
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.pierre.core.model.theme.Theme
import org.pierre.tvmaze.material_you.presentation.model.AndroidColorSchemeUiEvent
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton
import org.pierre.tvmaze.ui.theme.preview.PreviewTheme
import tvmaze.feature.material_you.presentation.generated.resources.Res
import tvmaze.feature.material_you.presentation.generated.resources.android_dynamic_colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialYouSwitchComponent(
    isChecked: Boolean,
    onEvent: (AndroidColorSchemeUiEvent) -> Unit,
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(Res.string.android_dynamic_colors))
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

@Preview
@Composable
private fun MaterialYouComponentPreview(
    @PreviewParameter(MaterialYouParameterProvider::class)
    param: MaterialYouComponentParameter,
) {
    PreviewTheme(param.theme) {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            MaterialYouSwitchComponent(isChecked = param.isOn, onEvent = {})
        }
    }
}

private class MaterialYouParameterProvider :
    PreviewParameterProvider<MaterialYouComponentParameter> {
    private val lightOn = MaterialYouComponentParameter(
        theme = Theme.LIGHT,
        isOn = true
    )
    private val darkOn = MaterialYouComponentParameter(
        theme = Theme.DARK,
        isOn = true
    )
    override val values: Sequence<MaterialYouComponentParameter> = sequenceOf(
        lightOn,
        darkOn,
        lightOn.copy(isOn = false),
        darkOn.copy(isOn = false),
    )
}

private data class MaterialYouComponentParameter(
    val theme: Theme,
    val isOn: Boolean,
)
