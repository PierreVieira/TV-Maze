package org.pierre.tvmaze.feature.media_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.favorites.domain.usecase.GetFavoritesFlow
import org.pierre.tvmaze.feature.favorites.domain.usecase.ToggleFavorite
import org.pierre.tvmaze.feature.media_details.domain.usecase.GetMediaDetails
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsRoute
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiAction
import org.pierre.tvmaze.feature.media_details.presentation.model.MediaDetailsUiEvent
import org.pierre.tvmaze.model.common.MediaItemModel
import org.pierre.tvmaze.model.data_status.DataStatus
import org.pierre.tvmaze.model.data_status.toLoadedData
import org.pierre.tvmaze.ui.utils.observe

class MediaDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    getFavoritesFlow: GetFavoritesFlow,
    private val getMediaDetails: GetMediaDetails,
    private val toggleFavorite: ToggleFavorite,
) : ViewModel() {

    private var favorites = emptyList<MediaItemModel>()

    private val _uiAction = Channel<MediaDetailsUiAction>()
    val uiAction: Flow<MediaDetailsUiAction> = _uiAction.receiveAsFlow()

    private val _uiState: MutableStateFlow<MediaItemModel> = MutableStateFlow(createLoadingModel())
    val uiState: StateFlow<MediaItemModel> = _uiState.asStateFlow()

    private val _isSummaryExpanded = MutableStateFlow(false)
    val isSummaryExpanded: StateFlow<Boolean> = _isSummaryExpanded.asStateFlow()

    private val mediaId: Long = savedStateHandle.toRoute<MediaDetailsRoute>().id

    init {
        observe(getFavoritesFlow()) { favorites ->
            this.favorites = favorites
            updateIsFavorite()
        }
        viewModelScope.launch {
            val result = getMediaDetails(mediaId)
            result.onSuccess { model ->
                _uiState.value = model
                updateIsFavorite()
            }
        }
    }

    private fun updateIsFavorite() {
        val isFav = favorites.any { it.id.toLoadedData() == mediaId }
        _uiState.value = _uiState.value.copy(isFavorite = DataStatus.Loaded(isFav))
    }

    fun onEvent(event: MediaDetailsUiEvent) {
        when (event) {
            MediaDetailsUiEvent.OnBackClick -> onBack()
            is MediaDetailsUiEvent.OnFavoriteClick -> onFavoriteClick(event.id)
            MediaDetailsUiEvent.OnToggleSummaryExpansion -> toggleSummaryExpansion()
        }
    }

    private fun toggleSummaryExpansion() {
        _isSummaryExpanded.value = !_isSummaryExpanded.value
    }

    private fun onBack() {
        viewModelScope.launch { _uiAction.send(MediaDetailsUiAction.NavigateBack) }
    }

    private fun onFavoriteClick(id: Long) {
        viewModelScope.launch {
            val current = _uiState.value
            if ((current.id as? DataStatus.Loaded)?.data == id) {
                toggleFavorite(current)
            }
        }
    }

    private fun createLoadingModel(): MediaItemModel = MediaItemModel(
        id = DataStatus.Loading,
        name = DataStatus.Loading,
        image = DataStatus.Loading,
        dates = DataStatus.Loading,
        stars = DataStatus.Loading,
        isFavorite = DataStatus.Loading,
        averageRanting = DataStatus.Loading,
        summary = DataStatus.Loading,
        genres = DataStatus.Loading,
    )
}
