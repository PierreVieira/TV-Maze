package org.pierre.feature.search.warning.delete_all.presentation.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import org.pierre.feature.search.warning.delete_all.presentation.model.DeleteAllSearchUiEvent
import tvmaze.feature.search.warning.delete_all.presentation.generated.resources.Res
import tvmaze.feature.search.warning.delete_all.presentation.generated.resources.delete_all_search_cancel
import tvmaze.feature.search.warning.delete_all.presentation.generated.resources.delete_all_search_confirm
import tvmaze.feature.search.warning.delete_all.presentation.generated.resources.delete_all_search_message
import tvmaze.feature.search.warning.delete_all.presentation.generated.resources.delete_all_search_title

@Composable
fun DeleteAllSearchDialog(
    onEvent: (DeleteAllSearchUiEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEvent(DeleteAllSearchUiEvent.DISMISS) },
        title = { Text(stringResource(Res.string.delete_all_search_title)) },
        text = {
            Text(stringResource(Res.string.delete_all_search_message))
        },
        confirmButton = {
            TextButton(
                onClick = { onEvent(DeleteAllSearchUiEvent.CONFIRM) },
            ) {
                Text(stringResource(Res.string.delete_all_search_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(DeleteAllSearchUiEvent.CANCEL) }) {
                Text(stringResource(Res.string.delete_all_search_cancel))
            }
        },
    )
}
