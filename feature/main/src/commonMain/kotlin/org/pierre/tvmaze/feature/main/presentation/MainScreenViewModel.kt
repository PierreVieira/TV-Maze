package org.pierre.tvmaze.feature.main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavRoute
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavigationItemModel
import org.pierre.tvmaze.feature.main.presentation.model.BottomNavigationItemPresentationModel
import org.pierre.tvmaze.feature.main.presentation.model.MainScreenUiAction
import org.pierre.tvmaze.feature.main.presentation.model.MainScreenUiEvent
import tvmaze.feature.main.generated.resources.Res
import tvmaze.feature.main.generated.resources.favorites
import tvmaze.feature.main.generated.resources.search
import tvmaze.feature.main.generated.resources.theme

class MainScreenViewModel : ViewModel() {
    private val routes: List<BottomNavRoute> = listOf(
        BottomNavRoute.Search,
        BottomNavRoute.Favorites,
        BottomNavRoute.Theme,
    )

    private val _uiAction: MutableSharedFlow<MainScreenUiAction> = MutableSharedFlow()
    val uiAction: SharedFlow<MainScreenUiAction> = _uiAction

    val bottomNavigationItemModels: List<BottomNavigationItemModel<Any>> =
        routes.map { it.toBottomNavHost() }

    fun onEvent(event: MainScreenUiEvent) {
        when (event) {
            is MainScreenUiEvent.BottomNavItemClicked ->
                emitAction(MainScreenUiAction.NavigateToBottomRoute(event.route))
        }
    }

    private fun emitAction(action: MainScreenUiAction) {
        viewModelScope.launch {
            _uiAction.emit(action)
        }
    }

    private fun BottomNavRoute.toBottomNavHost(): BottomNavigationItemModel<Any> =
        BottomNavigationItemModel(
            route = this,
            presentationModel = when (this) {
                BottomNavRoute.Search -> BottomNavigationItemPresentationModel(
                    title = Res.string.search,
                    icon = Icons.Default.Search,
                )

                BottomNavRoute.Favorites -> BottomNavigationItemPresentationModel(
                    title = Res.string.favorites,
                    icon = Icons.Default.Favorite,
                )

                BottomNavRoute.Theme -> BottomNavigationItemPresentationModel(
                    title = Res.string.theme,
                    icon = Icons.Default.Contrast,
                )
            }
        )
}
