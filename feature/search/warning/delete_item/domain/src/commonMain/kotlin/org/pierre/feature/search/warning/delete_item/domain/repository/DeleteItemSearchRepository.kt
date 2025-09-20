package org.pierre.feature.search.warning.delete_item.domain.repository

fun interface DeleteItemSearchRepository {
    suspend fun deleteSearchItem(id: Long)
}
