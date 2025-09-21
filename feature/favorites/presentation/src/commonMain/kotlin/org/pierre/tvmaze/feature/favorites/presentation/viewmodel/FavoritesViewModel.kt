package org.pierre.tvmaze.feature.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.pierre.tvmaze.core.utils.updateValue
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.favorites.presentation.model.FavoritesUiAction
import org.pierre.tvmaze.feature.favorites.presentation.model.FavoritesUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.utils.observe

class FavoritesViewModel(
    getFavoritesFlow: GetFavoritesFlow,
    private val toggleFavorite: ToggleFavorite,
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<MediaItemModel>>(emptyList())
    val uiState: StateFlow<List<MediaItemModel>> = _uiState.asStateFlow()

    private val _uiAction = Channel<FavoritesUiAction>()
    val uiAction: Flow<FavoritesUiAction> = _uiAction.receiveAsFlow()

    init {
        observe(
            flow = getFavoritesFlow(),
            collector = _uiState::updateValue,
        )
    }

    fun onEvent(event: FavoritesUiEvent) {
        when (event) {
            is FavoritesUiEvent.OnFavoriteItemClick -> onFavoriteClick(event.id)
            is FavoritesUiEvent.OnItemClick -> onItemClick(event.id)
        }
    }

    private fun onItemClick(id: Long) {
        viewModelScope.launch {
            _uiAction.send(FavoritesUiAction.NavigateToMediaDetails(id))
        }
    }

    private fun onFavoriteClick(id: Long) {
        viewModelScope.launch {
            _uiState.value.find { it.id.toLoadedData() == id }?.let {
                toggleFavorite(it)
            }
        }
    }
}
