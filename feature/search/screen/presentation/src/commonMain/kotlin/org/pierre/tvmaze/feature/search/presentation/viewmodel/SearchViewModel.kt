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
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchFactories
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiAction
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent
import org.pierre.tvmaze.model.common.ShowItemModel
import org.pierre.tvmaze.ui.utils.observe

class SearchViewModel(
    private val useCases: SearchUseCases,
    private val factories: SearchFactories,
) : ViewModel() {

    private var searchHistory: List<SearchHistoryItemModel> = emptyList()

    private val _uiAction: Channel<SearchUiAction> = Channel()
    val uiAction: Flow<SearchUiAction> = _uiAction.receiveAsFlow()

    private val _uiState = MutableStateFlow(factories.initialState.create())
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()

    init {
        observe(useCases.getSearchBarPositionFlow()) { position ->
            _uiState.update {
                it.copy(
                    searchBar = it.searchBar.copy(position = position)
                )
            }
        }
        observe(
            flow = useCases.getSearchHistoryFlow(),
            collector = ::onHistoryModelObserveCallback
        )
    }

    private fun onHistoryModelObserveCallback(models: List<SearchHistoryItemModel>) {
        searchHistory = models
        _uiState.update { currentState ->
            factories.newSearchStateFromHistory.create(searchHistory, currentState)
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

            SearchUiEvent.OnDeleteHistoryClick -> {
                hideMenu()
                viewModelScope.launch {
                    _uiAction.send(SearchUiAction.NavigateToDeleteAllSearchHistory)
                }
            }
            SearchUiEvent.OnDismissMenuClick,
                -> hideMenu()

            is SearchUiEvent.OnHistoryItemClick -> {
                onQueryChanged(uiEvent.itemName)
                onSearch()
            }

            is SearchUiEvent.OnFavoriteSearchResultItemClick -> Unit
            is SearchUiEvent.OnSearchResultItemClick -> Unit

            is SearchUiEvent.OnHistoryItemLongClick -> showDeleteItemDialog(
                name = uiEvent.name,
                id = uiEvent.id
            )

            is SearchUiEvent.OnHistoryItemDeleteClick -> showDeleteItemDialog(
                name = uiEvent.name,
                id = uiEvent.id
            )
        }
    }

    private fun showDeleteItemDialog(name: String, id: Long) {
        viewModelScope.launch {
            _uiAction.send(SearchUiAction.NavigateToDeleteSearchHistoryItem(id, name))
        }
    }

    private fun onChangeSearchBarPositionClick() {
        hideMenu()
        useCases.run {
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
        val previousState = uiState.value
        _uiState.update {
            it.copy(
                content = SearchContent.SearchResults(useCases.getSearchItemsLoading())
            )
        }
        viewModelScope.launch {
            useCases.run {
                search(query)
                    .onSuccess { shows: List<ShowItemModel> ->
                        _uiState.update {
                            it.copy(content = SearchContent.SearchResults(shows))
                        }
                    }
                    .onFailure { failure ->
                        _uiState.update { previousState }
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
                    iconsModel = factories.searchBarIcons.create(query),
                ),
                content = factories.searchHistoryContent.create(searchHistory, query),
            )
        }
    }

    private fun updateIconsModel() {
        _uiState.update {
            it.copy(
                searchBar = it.searchBar.copy(
                    iconsModel = factories.searchBarIcons.create(
                        query = it.searchBar.query
                    )
                )
            )
        }
    }
}
