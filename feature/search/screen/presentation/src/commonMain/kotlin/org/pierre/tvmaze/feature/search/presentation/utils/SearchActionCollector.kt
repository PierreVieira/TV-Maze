package org.pierre.tvmaze.feature.search.presentation.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.stringResource
import org.pierre.tvmaze.feature.search.domain.model.error.SearchErrorType
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiAction
import org.pierre.tvmaze.ui.utils.ActionCollector
import tvmaze.feature.search.screen.presentation.generated.resources.Res
import tvmaze.feature.search.screen.presentation.generated.resources.search_error_empty_query
import tvmaze.feature.search.screen.presentation.generated.resources.search_error_network
import tvmaze.feature.search.screen.presentation.generated.resources.search_error_not_possible_to_perform_favorite_toggle
import tvmaze.feature.search.screen.presentation.generated.resources.search_error_unknown

@Composable
fun SearchActionCollector(
    uiAction: Flow<SearchUiAction>,
    snackbarHostState: SnackbarHostState,
    goToDeleteSearchHistoryItem: (id: Long, name: String) -> Unit,
    goToDeleteAllSearchHistory: () -> Unit,
    goToMediaDetails: (id: Long) -> Unit,
) {
    val emptyQueryMsg = stringResource(Res.string.search_error_empty_query)
    val networkErrorMsg = stringResource(Res.string.search_error_network)
    val unknownErrorMsg = stringResource(Res.string.search_error_unknown)
    val notPossibleToPerformFavoriteToggleMsg = stringResource(
        Res.string.search_error_not_possible_to_perform_favorite_toggle
    )

    ActionCollector(uiAction) { action ->
        when (action) {
            is SearchUiAction.ShowError -> {
                snackbarHostState.showSnackbar(
                    when (action.errorType) {
                        SearchErrorType.EMPTY_QUERY -> emptyQueryMsg
                        SearchErrorType.NETWORK_ERROR -> networkErrorMsg
                        SearchErrorType.NOT_POSSIBLE_TO_PERFORM_FAVORITE_TOGGLE -> notPossibleToPerformFavoriteToggleMsg
                        SearchErrorType.UNKNOWN_ERROR -> unknownErrorMsg
                    }
                )
            }

            is SearchUiAction.NavigateToDeleteSearchHistoryItem -> goToDeleteSearchHistoryItem(
                action.id,
                action.name
            )
            SearchUiAction.NavigateToDeleteAllSearchHistory -> goToDeleteAllSearchHistory()
            is SearchUiAction.NavigateToMediaDetails -> goToMediaDetails(action.id)
        }
    }
}
