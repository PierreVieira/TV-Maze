package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteShowEntity
import org.pierre.tvmaze.model.common.ShowItemModel

interface FavoriteShowEntityMapper {
    fun mapOrNull(model: ShowItemModel): FavoriteShowEntity?
}
