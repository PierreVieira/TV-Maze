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
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.feature.search.domain.model.SearchUseCases
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchHistoryContentFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiAction
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.ui.utils.observe

class SearchViewModel(
    initialSearchStateFactory: InitialSearchStateFactory,
    private val searchBarIconsFactory: SearchBarIconsFactory,
    private val searchHistoryContentFactory: SearchHistoryContentFactory,
    private val searchUseCases: SearchUseCases,
) : ViewModel() {

    private var searchHistory: List<SearchHistoryItemModel> = emptyList()

    private val _uiAction: Channel<SearchUiAction> = Channel()
    val uiAction: Flow<SearchUiAction> = _uiAction.receiveAsFlow()

    private val _uiState = MutableStateFlow(initialSearchStateFactory.create())
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()

    init {
        observe(searchUseCases.getSearchBarPositionFlow()) { position ->
            _uiState.update {
                it.copy(
                    searchBar = it.searchBar.copy(position = position)
                )
            }
        }
        observe(searchUseCases.getSearchHistoryFlow()) {
            searchHistory = it
            if (_uiState.value.content is SearchContent.Error.NoHistory && searchHistory.isNotEmpty()) {
                _uiState.update { currentState ->
                    currentState.copy(content = SearchContent.History(searchHistory))
                }
            }
        }
    }

    fun onEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.OnQueryChange -> {
                onQueryChanged(uiEvent.query)
            }

            is SearchUiEvent.OnSearch -> onSearch()

            SearchUiEvent.OnClearClick -> {
                onQueryChanged("")
                updateIconsModel()
            }

            SearchUiEvent.OnSearchIconClick -> onSearch()
            SearchUiEvent.OnMoreOptionsClick -> _uiState.update {
                it.copy(
                    searchBar = it.searchBar.copy(
                        isShowingMenu = true
                    )
                )
            }

            SearchUiEvent.OnChangeSearchBarPositionClick -> onChangeSearchBarPositionClick()

            SearchUiEvent.OnDeleteHistoryClick,
            SearchUiEvent.OnDismissMenuClick,
                -> hideMenu()

            is SearchUiEvent.OnHistoryItemClick -> {
                onQueryChanged(uiEvent.itemName)
                onSearch()
            }
            is SearchUiEvent.OnFavoriteSearchResultItemClick -> Unit
            is SearchUiEvent.OnSearchResultItemClick -> Unit

            is SearchUiEvent.OnHistoryItemLongClick,
            is SearchUiEvent.OnHistoryItemDeleteClick,
                -> Unit
        }
    }

    private fun onChangeSearchBarPositionClick() {
        hideMenu()
        searchUseCases.run {
            val newSearchBarPosition = getNewSearchBarPositionDueToToggle(
                currentPosition = uiState.value.searchBar.position
            )
            viewModelScope.launch {
                delay(getDurationDisappearMenuDuration())
                saveNewSearchBarPosition(newSearchBarPosition)
            }
        }
    }

    private fun hideMenu() {
        _uiState.update {
            it.copy(
                searchBar = it.searchBar.copy(
                    isShowingMenu = false
                )
            )
        }
    }

    private fun onSearch() {
        val query = uiState.value.searchBar.query
        hideMenu()
        _uiState.update {
            it.copy(
                content = SearchContent.SearchResults(searchUseCases.getSearchItemsLoading())
            )
        }
        viewModelScope.launch {
            searchUseCases.run {
                search(query)
                    .onSuccess { shows: List<ShowItemModel> ->
                        _uiState.update {
                            it.copy(content = SearchContent.SearchResults(shows))
                        }
                    }
                    .onFailure { failure ->
                        _uiAction.send(SearchUiAction.ShowError(getErrorTypeFromThrowable(failure)))
                    }
            }
        }
    }

    private fun onQueryChanged(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchBar = currentState.searchBar.copy(
                    query = query,
                    iconsModel = searchBarIconsFactory.create(
                        query = query
                    ),
                ),
                content = searchHistoryContentFactory.create(searchHistory, query),
            )
        }
    }

    private fun updateIconsModel() {
        _uiState.update {
            it.copy(
                searchBar = it.searchBar.copy(
                    iconsModel = searchBarIconsFactory.create(
                        query = it.searchBar.query
                    )
                )
            )
        }
    }
}
