package org.pierre.feature.search.warning.delete_all.domain.repository

fun interface DeleteAllSearchRepository {
    suspend fun deleteAll()
}
