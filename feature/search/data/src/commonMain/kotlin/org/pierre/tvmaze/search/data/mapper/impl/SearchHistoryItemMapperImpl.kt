package org.pierre.tvmaze.search.data.mapper.impl

import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel
import org.pierre.tvmaze.search.data.mapper.SearchHistoryItemMapper

class SearchHistoryItemMapperImpl: SearchHistoryItemMapper {
    override fun mapToDomain(entity: SearchHistoryItemEntity): SearchHistoryItemModel = entity.run {
        SearchHistoryItemModel(
            query = query,
            timestamp = timestamp,
            id = id,
            searchedRange = IntRange.EMPTY,
        )
    }

    override fun mapToEntity(model: SearchHistoryItemModel): SearchHistoryItemEntity = model.run {
        SearchHistoryItemEntity(
            query = query,
            timestamp = timestamp,
            id = id,
        )
    }
}
