package org.pierre.tvmaze.mapper.impl

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pierre.tvmaze.dto.image.ImageDto
import org.pierre.tvmaze.model.common.image.ImagesModel

class MediaModelMapperImplTest {

    private val mapper = MediaModelMapperImpl()

    @Test
    fun `given image dto with both urls when map then returns images model with same values`() {
        // Given
        val dto = ImageDto(
            medium = "https://example.com/medium.jpg",
            original = "https://example.com/original.jpg",
        )

        // When
        val model = mapper.map(dto)

        // Then
        val expected = ImagesModel(
            medium = dto.medium,
            original = dto.original,
        )
        assertEquals(expected, model)
    }

    @Test
    fun `given image dto with empty strings when map then returns images model with empty strings`() {
        // Given
        val dto = ImageDto(
            medium = "",
            original = "",
        )

        // When
        val model = mapper.map(dto)

        // Then
        val expected = ImagesModel(
            medium = "",
            original = "",
        )
        assertEquals(expected, model)
    }

    @Test
    fun `given image dto with only original text when map then returns images model with original and null medium`() {
        // Given
        val dto = ImageDto(
            medium = "",
            original = "https://example.com/original.jpg",
        )

        // When
        val model = mapper.map(dto)

        // Then
        val expected = ImagesModel(
            medium = "",
            original = dto.original,
        )
        assertEquals(expected, model)
    }
}
