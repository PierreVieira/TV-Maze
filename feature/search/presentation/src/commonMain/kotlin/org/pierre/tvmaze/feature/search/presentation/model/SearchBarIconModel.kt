package org.pierre.tvmaze.feature.search.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

data class SearchBarIconModel(
    val imageVector: ImageVector,
    val contentDescription: StringResource,
    val uiEvent: SearchUiEvent,
)
