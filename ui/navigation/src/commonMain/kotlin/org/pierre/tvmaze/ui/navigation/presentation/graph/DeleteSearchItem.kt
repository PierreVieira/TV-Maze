package org.pierre.tvmaze.ui.navigation.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import org.koin.compose.viewmodel.koinViewModel
import org.pierre.feature.search.warning.delete_item.presentation.dialog.DeleteItemSearchDialog
import org.pierre.feature.search.warning.delete_item.presentation.model.DeleteSearchItemDialogRoute
import org.pierre.feature.search.warning.delete_item.presentation.viewmodel.DeleteSearchItemViewModel
import org.pierre.tvmaze.ui.utils.ActionCollector

internal fun NavGraphBuilder.deleteSearchItem(navHostController: NavHostController) {
    dialog<DeleteSearchItemDialogRoute> {
        val viewModel: DeleteSearchItemViewModel = koinViewModel()
        ActionCollector(viewModel.navigateBackUiAction) {
            navHostController.navigateUp()
        }
        DeleteItemSearchDialog(
            itemName = viewModel.itemName,
            onEvent = viewModel::onEvent,
        )
    }
}
