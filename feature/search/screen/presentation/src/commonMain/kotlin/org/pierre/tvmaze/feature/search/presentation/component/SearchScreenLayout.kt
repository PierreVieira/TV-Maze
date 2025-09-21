package org.pierre.tvmaze.feature.search.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.ui.components.spacer.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreenLayout(
    snackbarHostState: SnackbarHostState,
    searchBarPosition: SearchBarPosition,
    searchBarComponent: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val isSearchBarOnTop = searchBarPosition == SearchBarPosition.TOP
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (isSearchBarOnTop) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    searchBarComponent()
                    VerticalSpacer(8)
                }
            }
        },
        bottomBar = {
            if (!isSearchBarOnTop) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    searchBarComponent()
                }
            }
        }
    ) { paddingValues ->
        val contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            bottom = if (isSearchBarOnTop) {
                paddingValues.calculateBottomPadding()
            } else {
                // Reduce the reserved space when the search bar is at the bottom to avoid a large visual gap
                (paddingValues.calculateBottomPadding() - 48.dp).coerceAtLeast(0.dp)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content()
        }
    }
}
