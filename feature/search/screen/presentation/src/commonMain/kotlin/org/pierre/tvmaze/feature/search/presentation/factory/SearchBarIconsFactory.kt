package org.pierre.tvmaze.feature.search.presentation.factory

import org.pierre.tvmaze.feature.search.presentation.model.bar.SearchBarIconsModel

fun interface SearchBarIconsFactory {
    fun create(query: String): SearchBarIconsModel
}
