package org.pierre.tvmaze.switch_android_color_scheme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.switch_android_color_scheme.presentation.R
import org.pierre.tvmaze.switch_android_color_scheme.presentation.model.AndroidColorSchemeUiEvent
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialYouBottomSheet(
    sheetState: SheetState,
    isMaterialYouActivated: Boolean,
    onEvent: (AndroidColorSchemeUiEvent) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onEvent(AndroidColorSchemeUiEvent.OnInformationDialogDismiss) },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .widthIn(max = 560.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(R.string.dynamic_colors_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.dynamic_colors_toggle_label),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f),
                )
                Switch(
                    checked = isMaterialYouActivated,
                    onCheckedChange = {
                        onEvent(AndroidColorSchemeUiEvent.OnIsDynamicColorsEnabledChange(it))
                    },
                )
            }

            Text(
                text = stringResource(R.string.dynamic_colors_message),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = stringResource(R.string.dynamic_colors_compat_note),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            VerticalSpacer(size = 4)

            Button(
                onClick = { onEvent(AndroidColorSchemeUiEvent.BottomSheetGotItClick) },
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = stringResource(R.string.got_it))
            }
        }
    }
}
