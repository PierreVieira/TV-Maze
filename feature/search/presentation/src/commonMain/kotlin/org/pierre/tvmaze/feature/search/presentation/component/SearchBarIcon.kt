package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.feature.search.presentation.model.SearchBarIconModel
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.ui.components.icon_button.CommonIconButton

@Composable
fun SearchBarIcon(
    modifier: Modifier = Modifier,
    model: SearchBarIconModel,
    onEvent: (SearchUiEvent) -> Unit,
) {
    model.run {
        CommonIconButton(
            modifier = modifier,
            imageVector = imageVector,
            contentDescription = contentDescription,
            onClick = { onEvent(uiEvent) }
        )
    }
}
