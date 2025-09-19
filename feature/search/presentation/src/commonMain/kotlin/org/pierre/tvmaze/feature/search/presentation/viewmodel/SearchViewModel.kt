package org.pierre.tvmaze.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.search.domain.model.SearchUseCases
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

class SearchViewModel(
    initialSearchStateFactory: InitialSearchStateFactory,
    private val searchBarIconsFactory: SearchBarIconsFactory,
    private val searchUseCases: SearchUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialSearchStateFactory.create())
    val uiState = _uiState.asStateFlow()

    init {
        searchUseCases.getSearchBarPositionFlow().onEach { position ->
            _uiState.update {
                it.copy(
                    searchBarPosition = position
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.OnExpandedChange -> setExpanded(isExpanded = uiEvent.expanded)

            is SearchUiEvent.OnQueryChange -> onQueryChanged(uiEvent.query.trim())

            is SearchUiEvent.OnSearch -> {
                this@SearchViewModel.onSearch(uiEvent.query)
                setExpanded(false)
            }

            SearchUiEvent.OnClearClick -> onQueryChanged("")
            SearchUiEvent.OnSearchIconClick -> setExpanded(true)
            SearchUiEvent.OnArrowBackClick -> setExpanded(false)
            SearchUiEvent.OnMoreOptionsClick -> _uiState.update { it.copy(isShowingMenu = true) }
            SearchUiEvent.OnChangeSearchBarPositionClick -> onChangeSearchBarPositionClick()

            SearchUiEvent.OnDeleteHistoryClick,
            SearchUiEvent.OnDismissMenuClick,
                -> _uiState.update { it.copy(isShowingMenu = false) }
        }
    }

    private fun onChangeSearchBarPositionClick() {
        _uiState.update {
            it.copy(
                isShowingMenu = false,
            )
        }
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

    private fun onSearch(query: String) {
        viewModelScope.launch {
            searchUseCases.search(query)
        }
    }

    private fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                query = query,
                iconsModel = searchBarIconsFactory.create(
                    isExpanded = it.isExpanded,
                    query = query
                )
            )
        }
    }

    private fun setExpanded(isExpanded: Boolean) {
        _uiState.update {
            it.copy(
                isExpanded = isExpanded,
                iconsModel = searchBarIconsFactory.create(
                    isExpanded = isExpanded,
                    query = it.query
                )
            )
        }
    }
}
