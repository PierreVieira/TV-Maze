package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.model.common.media.StarsModel

class StarsMapperImplTest {

    private val mapper = StarsMapperImpl()

    @Test
    fun `given average below zero when map then clamps to zero stars`() {
        // When
        val model = mapper.map(-5.0)
        // Then
        assertEquals(StarsModel(fullStarsAmount = 0, showInAHalf = false), model)
    }

    @Test
    fun `given average above ten when map then clamps to five full stars`() {
        // When
        val model = mapper.map(15.0)
        // Then
        assertEquals(StarsModel(fullStarsAmount = 5, showInAHalf = false), model)
    }

    @Test
    fun `given average 7_0 when map then returns three full and a half star`() {
        // When
        val model = mapper.map(7.0)
        // Then
        assertEquals(StarsModel(fullStarsAmount = 3, showInAHalf = true), model)
    }

    @Test
    fun `given average 8_0 when map then returns four full stars`() {
        // When
        val model = mapper.map(8.0)
        // Then
        assertEquals(StarsModel(fullStarsAmount = 4, showInAHalf = false), model)
    }

    @Test
    fun `given average 9_9 when map then returns four full and a half star`() {
        // When
        val model = mapper.map(9.9)
        // Then
        assertEquals(StarsModel(fullStarsAmount = 4, showInAHalf = true), model)
    }
}
