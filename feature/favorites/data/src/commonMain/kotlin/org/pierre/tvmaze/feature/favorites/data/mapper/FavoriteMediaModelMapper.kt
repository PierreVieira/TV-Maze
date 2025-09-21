package org.pierre.tvmaze.feature.favorites.data.mapper

import org.pierre.tvmaze.core.room_provider.entity.FavoriteMediaEntity
import org.pierre.tvmaze.model.common.media.MediaItemModel

fun interface FavoriteMediaModelMapper {
    fun map(entity: FavoriteMediaEntity): MediaItemModel
}
