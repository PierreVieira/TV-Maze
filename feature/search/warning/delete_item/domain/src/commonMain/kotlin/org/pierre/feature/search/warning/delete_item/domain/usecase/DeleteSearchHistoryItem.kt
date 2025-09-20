package org.pierre.feature.search.warning.delete_item.domain.usecase

interface DeleteSearchHistoryItem {
    suspend operator fun invoke(id: Long)
}
