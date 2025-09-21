package org.pierre.tvmaze.feature.search.presentation.factory.impl

import org.pierre.tvmaze.feature.search.domain.model.SearchBarPosition
import org.pierre.tvmaze.feature.search.presentation.factory.InitialSearchStateFactory
import org.pierre.tvmaze.feature.search.presentation.factory.SearchBarIconsFactory
import org.pierre.tvmaze.feature.search.presentation.model.SearchContent
import org.pierre.tvmaze.feature.search.presentation.model.SearchState
import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarState

internal class InitialSearchStateFactoryImpl(
    private val searchBarIconsFactory: SearchBarIconsFactory,
) : InitialSearchStateFactory {

    override fun create(): SearchState = SearchState(
        searchBar = getInitialSearchBarState(),
        content = SearchContent.Loading,
    )

    private fun getInitialSearchBarState(): SearchBarState = SearchBarState(
        query = INITIAL_QUERY,
        isShowingMenu = INITIAL_IS_SHOWING_MENU,
        iconsModel = searchBarIconsFactory.create(
            query = INITIAL_QUERY,
        ),
        position = SearchBarPosition.TOP,
    )

    companion object {
        private const val INITIAL_IS_SHOWING_MENU = false
        private const val INITIAL_QUERY = ""
    }
}
