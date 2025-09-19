package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.component.SearchBarComponent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchUiEvent) -> Unit,
) {
    // Target bias: top = -1f, bottom = 1f
    val targetBias = if (state.searchBarPosition == SearchBarPosition.TOP) -1f else 1f
    val animatedBias by animateFloatAsState(
        targetValue = targetBias,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "searchBarBias",
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        SearchBarComponent(
            modifier = Modifier.align(
                BiasAlignment(
                    horizontalBias = 0f,
                    verticalBias = animatedBias
                )
            ),
            state = state,
            onEvent = onEvent,
        )
    }
}
