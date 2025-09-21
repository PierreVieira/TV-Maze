package org.pierre.tvmaze.feature.episodes.presentation.factory.impl

import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.feature.episodes.presentation.factory.LoadingSeasonsFactory
import org.pierre.tvmaze.model.data_status.DataStatusUtils.loading

class LoadingSeasonsFactoryImpl: LoadingSeasonsFactory {

    private val loadingSeason = SeasonModel(
        mediaId = 0,
        number = loading,
        episodes = emptyList(),
        isCollapsed = true,
    )

    private val loadingSeasons = List(5) { loadingSeason }

    override fun create(): List<SeasonModel> = loadingSeasons
}
