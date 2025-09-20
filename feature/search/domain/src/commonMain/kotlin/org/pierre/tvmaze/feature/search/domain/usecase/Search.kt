package org.pierre.tvmaze.feature.search.domain.usecase

import org.pierre.tvmaze.model.common.ShowItemModel

fun interface Search {
    suspend operator fun invoke(query: String): Result<List<ShowItemModel>>
}
