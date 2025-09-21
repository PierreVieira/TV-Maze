package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.feature.search.warning.delete_all.presentation.dialog.DeleteAllSearchDialog
import org.pierre.feature.search.warning.delete_all.presentation.model.DeleteAllSearchDialogRoute
import org.pierre.feature.search.warning.delete_all.presentation.viewmodel.DeleteAllSearchViewModel
import org.pierre.tvmaze.ui.utils.ActionCollector

internal fun NavGraphBuilder.deleteAllSearch(navHostController: NavHostController) {
    dialog<DeleteAllSearchDialogRoute> {
        val viewModel: DeleteAllSearchViewModel = koinViewModel()
        ActionCollector(viewModel.navigateBackUiAction) {
            navHostController.navigateUp()
        }
        DeleteAllSearchDialog(
            onEvent = viewModel::onEvent,
        )
    }
}
