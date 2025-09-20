package org.pierre.feature.search.warning.delete_item.domain.usecase.impl

import org.pierre.feature.search.warning.delete_item.domain.repository.DeleteItemSearchRepository
import org.pierre.feature.search.warning.delete_item.domain.usecase.DeleteSearchHistoryItem

internal class DeleteSearchHistoryItemUseCase(
    private val repository: DeleteItemSearchRepository
): DeleteSearchHistoryItem {
    override suspend fun invoke(id: Long) {
        repository.deleteSearchItem(id)
    }
}
