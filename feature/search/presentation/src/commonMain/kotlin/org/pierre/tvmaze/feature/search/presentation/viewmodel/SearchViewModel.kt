package org.pierre.tvmaze.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.search.domain.model.SearchUseCases
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiAction
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.ui.utils.observe

class SearchViewModel(
    initialSearchStateFactory: InitialSearchStateFactory,
    private val searchBarIconsFactory: SearchBarIconsFactory,
    private val searchUseCases: SearchUseCases,
) : ViewModel() {

    private val _uiAction: Channel<SearchUiAction> = Channel()
    val uiAction: Flow<SearchUiAction> = _uiAction.receiveAsFlow()

    private val _uiState = MutableStateFlow(initialSearchStateFactory.create())
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()

    init {
        observe(searchUseCases.getSearchBarPositionFlow()) { position ->
            _uiState.update {
                it.copy(
                    searchBarPosition = position
                )
            }
        }
    }

    fun onEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.OnQueryChange -> onQueryChanged(uiEvent.query)

            is SearchUiEvent.OnSearch -> onSearch()

            SearchUiEvent.OnClearClick -> {
                onQueryChanged("")
                updateIconsModel()
            }

            SearchUiEvent.OnSearchIconClick -> onSearch()
            SearchUiEvent.OnMoreOptionsClick -> _uiState.update { it.copy(isShowingMenu = true) }
            SearchUiEvent.OnChangeSearchBarPositionClick -> onChangeSearchBarPositionClick()

            SearchUiEvent.OnDeleteHistoryClick,
            SearchUiEvent.OnDismissMenuClick,
                -> _uiState.update { it.copy(isShowingMenu = false) }
        }
    }

    private fun onChangeSearchBarPositionClick() {
        searchUseCases.run {
            val newSearchBarPosition = getNewSearchBarPositionDueToToggle(
                currentPosition = uiState.value.searchBarPosition
            )
            viewModelScope.launch {
                delay(getDurationDisappearMenuDuration())
                saveNewSearchBarPosition(newSearchBarPosition)
            }
        }
    }

    private fun onSearch() {
        val query = uiState.value.query
        _uiState.update {
            it.copy(
                isShowingMenu = false,
                searchItems = searchUseCases.getSearchItemsLoading()
            )
        }
        viewModelScope.launch {
            searchUseCases.run {
                search(query)
                    .onSuccess { shows: List<ShowItemModel> ->
                        _uiState.update {
                            it.copy(searchItems = shows)
                        }
                    }
                    .onFailure { failure ->
                        _uiState.update {
                            it.copy(searchItems = emptyList())
                        }
                        _uiAction.send(SearchUiAction.ShowError(getErrorTypeFromThrowable(failure)))
                    }
            }
        }
    }

    private fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                query = query,
                iconsModel = searchBarIconsFactory.create(
                    query = query
                ),
                searchItems = if (query.isEmpty()) {
                    emptyList()
                } else {
                    it.searchItems
                }
            )
        }
    }

    private fun updateIconsModel() {
        _uiState.update {
            it.copy(
                iconsModel = searchBarIconsFactory.create(
                    query = it.query
                )
            )
        }
    }
}
