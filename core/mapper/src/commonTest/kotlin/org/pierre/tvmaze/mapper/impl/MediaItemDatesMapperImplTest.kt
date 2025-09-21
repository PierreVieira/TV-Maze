package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.pierre.tvmaze.model.common.media.MediaItemDatesModel

class MediaItemDatesMapperImplTest {

    private val mapper = MediaItemDatesMapperImpl()

    @Test
    fun `given running status with premiered date when map then returns Running with start year`() {
        // Given
        val status = "Running"
        val premiered = "2015-06-01"
        val ended: String? = null

        // When
        val model = mapper.map(status, premiered, ended)

        // Then
        assertEquals(MediaItemDatesModel.Running(startYear = 2015), model)
    }

    @Test
    fun `given ended status with both dates when map then returns StartAndEnd`() {
        // Given
        val status = "Ended"
        val premiered = "2009-10-10"
        val ended = "2015-10-10"

        // When
        val model = mapper.map(status, premiered, ended)

        // Then
        assertEquals(MediaItemDatesModel.StartAndEnd(startYear = 2009, endYear = 2015), model)
    }

    @Test
    fun `given no status but both dates when map then returns StartAndEnd`() {
        // Given
        val status: String? = null
        val premiered = "2010-01-01"
        val ended = "2012-12-31"

        // When
        val model = mapper.map(status, premiered, ended)

        // Then
        assertEquals(MediaItemDatesModel.StartAndEnd(startYear = 2010, endYear = 2012), model)
    }

    @Test
    fun `given only premiered date when map then returns Running`() {
        // Given
        val status: String? = null
        val premiered = "1999-07-07"
        val ended: String? = null

        // When
        val model = mapper.map(status, premiered, ended)

        // Then
        assertEquals(MediaItemDatesModel.Running(startYear = 1999), model)
    }

    @Test
    fun `given invalid or missing dates when map then returns null`() {
        // Given
        val status: String? = null
        val premiered: String? = null
        val ended: String? = null

        // When
        val model = mapper.map(status, premiered, ended)

        // Then
        assertNull(model)
    }
}
