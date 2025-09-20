package org.pierre.tvmaze.feature.search.presentation.model.bar

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

data class SearchBarIconModel(
    val imageVector: ImageVector,
    val contentDescription: StringResource,
    val uiEvent: SearchUiEvent,
)
