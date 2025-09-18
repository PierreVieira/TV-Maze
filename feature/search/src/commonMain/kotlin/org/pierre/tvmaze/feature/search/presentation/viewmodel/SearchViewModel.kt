package org.pierre.tvmaze.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pierre.tvmaze.feature.search.domain.usecase.SearchUseCase
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchUiEvent

class SearchViewModel(
    initialSearchStateFactory: InitialSearchStateFactory,
    private val searchBarIconsFactory: SearchBarIconsFactory,
    private val onSearchUseCase: SearchUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(initialSearchStateFactory.create())
    val state = _state.asStateFlow()

    fun onEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.OnExpandedChange -> setExpanded(isExpanded = uiEvent.expanded)

            is SearchUiEvent.OnQueryChange -> updateQuery(uiEvent.query)

            is SearchUiEvent.OnSearch -> {
                onSearch(uiEvent.query)
                setExpanded(false)
            }

            SearchUiEvent.OnClearClick -> updateQuery("")
            SearchUiEvent.OnSearchIconClick -> setExpanded(true)
            SearchUiEvent.OnArrowBackClick -> setExpanded(false)
        }
    }

    private fun onSearch(query: String) {
        viewModelScope.launch {
            onSearchUseCase(query)
        }
    }

    private fun updateQuery(query: String) {
        _state.update {
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
        _state.update {
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
