package org.pierre.tvmaze.feature.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pierre.tvmaze.core.utils.updateValue
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.favorites.presentation.model.FavoritesUiEvent
import org.pierre.tvmaze.model.common.MediaItemCard
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.utils.observe

class FavoritesViewModel(
    getFavoritesFlow: GetFavoritesFlow,
    private val toggleFavorite: ToggleFavorite,
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<MediaItemCard>>(emptyList())
    val uiState: StateFlow<List<MediaItemCard>> = _uiState.asStateFlow()

    init {
        observe(
            flow = getFavoritesFlow(),
            collector = _uiState::updateValue,
        )
    }

    fun onEvent(event: FavoritesUiEvent) {
        when (event) {
            is FavoritesUiEvent.OnFavoriteItemClick -> onFavoriteClick(event.id)
            is FavoritesUiEvent.OnItemClick -> Unit // No-op for now
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
