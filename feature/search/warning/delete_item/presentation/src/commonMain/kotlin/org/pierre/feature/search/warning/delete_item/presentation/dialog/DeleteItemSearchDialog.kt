package org.pierre.feature.search.warning.delete_item.presentation.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import org.pierre.feature.search.warning.delete_item.presentation.model.DeleteSearchItemUiEvent
import tvmaze.feature.search.warning.delete_item.presentation.generated.resources.Res
import tvmaze.feature.search.warning.delete_item.presentation.generated.resources.delete_search_item_cancel
import tvmaze.feature.search.warning.delete_item.presentation.generated.resources.delete_search_item_confirm
import tvmaze.feature.search.warning.delete_item.presentation.generated.resources.delete_search_item_message
import tvmaze.feature.search.warning.delete_item.presentation.generated.resources.delete_search_item_title

@Composable
fun DeleteItemSearchDialog(
    itemName: String,
    onEvent: (DeleteSearchItemUiEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onEvent(DeleteSearchItemUiEvent.DISMISS) },
        title = { Text(stringResource(Res.string.delete_search_item_title)) },
        text = {
            Text(stringResource(Res.string.delete_search_item_message, itemName))
        },
        confirmButton = {
            TextButton(
                onClick = { onEvent(DeleteSearchItemUiEvent.CONFIRM) },
            ) {
                Text(stringResource(Res.string.delete_search_item_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onEvent(DeleteSearchItemUiEvent.CANCEL) }) {
                Text(stringResource(Res.string.delete_search_item_cancel))
            }
        },
    )
}
