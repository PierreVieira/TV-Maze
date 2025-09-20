package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarState
import tvmaze.feature.search.screen.presentation.generated.resources.Res
import tvmaze.feature.search.screen.presentation.generated.resources.delete_history
import tvmaze.feature.search.screen.presentation.generated.resources.move_to_bottom
import tvmaze.feature.search.screen.presentation.generated.resources.move_to_top

@Composable
internal fun SearchOptionsMenu(
    state: SearchBarState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    DropdownMenu(
        expanded = state.isShowingMenu,
        onDismissRequest = { onEvent(SearchUiEvent.OnDismissMenuClick) }
    ) {
        val searchBarPosition = state.position
        val firstOptionText = stringResource(searchBarPosition.toStringResource())
        val firstOptionIcon = searchBarPosition.toImageVector()
        DropdownMenuItem(
            text = {
                Text(firstOptionText)
            },
            leadingIcon = {
                Icon(
                    imageVector = firstOptionIcon,
                    contentDescription = firstOptionText
                )
            },
            onClick = { onEvent(SearchUiEvent.OnChangeSearchBarPositionClick) }
        )
        val deleteColor = MaterialTheme.colorScheme.error
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(resource = Res.string.delete_history),
                    color = deleteColor,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(Res.string.delete_history),
                    tint = deleteColor,
                )
            },
            onClick = { onEvent(SearchUiEvent.OnDeleteHistoryClick) }
        )
    }
}

private fun SearchBarPosition.toStringResource(): StringResource = when (this) {
    SearchBarPosition.TOP -> Res.string.move_to_bottom
    SearchBarPosition.BOTTOM -> Res.string.move_to_top
}

private fun SearchBarPosition.toImageVector(): ImageVector = when (this) {
    SearchBarPosition.TOP -> Icons.Default.ArrowDownward
    SearchBarPosition.BOTTOM -> Icons.Default.ArrowUpward
}
