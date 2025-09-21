package org.pierre.tvmaze.feature.episodes.presentation.factory.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import org.pierre.tvmaze.feature.episodes.domain.model.SeasonModel
import org.pierre.tvmaze.model.data_status.DataStatus

class LoadingSeasonsFactoryImplTest {

    private lateinit var factory: LoadingSeasonsFactoryImpl

    @Test
    fun `given factory when create then returns five collapsed loading seasons`() {
        prepareScenario()
        // When
        val seasons: List<SeasonModel> = factory.create()

        // Then
        assertEquals(5, seasons.size)
        // All seasons should be collapsed and have number as Loading
        seasons.forEach { season ->
            assertTrue(season.isCollapsed, "Expected collapsed=true for loading placeholder")
            assertIs<DataStatus.Loading>(season.number)
            assertTrue(season.episodes.isEmpty(), "Expected no episodes in loading placeholder")
            assertEquals(0L, season.mediaId)
        }
    }

    @Test
    fun `given factory when create then all items are equal placeholders`() {
        prepareScenario()
        // When
        val seasons = factory.create()

        // Then
        // They are created from the same prototype, equality should hold pairwise
        assertTrue(seasons.zipWithNext().all { (a, b) -> a == b }, "All placeholders should be equal")
    }

    private fun prepareScenario() {
        factory = LoadingSeasonsFactoryImpl()
    }
}
