package org.pierre.tvmaze.feature.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    searchBarPosition: SearchBarPosition,
    searchBarComponent: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val isSearchBarOnTop = searchBarPosition == SearchBarPosition.TOP
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CentralizedRow(
                isVisible = isSearchBarOnTop,
                content = searchBarComponent
            )
        },
        bottomBar = {
            CentralizedRow(
                isVisible = !isSearchBarOnTop,
                content = searchBarComponent
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Composable
private fun CentralizedRow(
    isVisible: Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    if (!isVisible) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}
