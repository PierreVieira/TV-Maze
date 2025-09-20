package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.presentation.model.SearchState

fun interface InitialSearchStateFactory {
    fun create(): SearchState
}
