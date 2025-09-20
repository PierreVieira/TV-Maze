package org.pierre.tvmaze.search.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.SearchHistoryItemEntity
import org.pierre.tvmaze.feature.search.domain.model.SearchHistoryItemModel

interface SearchHistoryItemMapper {
    fun mapToDomain(entity: SearchHistoryItemEntity): SearchHistoryItemModel
    fun mapToEntity(model: SearchHistoryItemModel): SearchHistoryItemEntity
}
