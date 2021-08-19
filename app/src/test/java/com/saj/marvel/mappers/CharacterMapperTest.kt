package com.saj.marvel.mappers

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.dtos.ThumbnailDTO
import com.saj.marvel.network.mappers.CharacterMapper
import com.saj.marvel.network.mappers.Mapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class CharacterMapperTest {

    private val mockThumbnailMapper = mockk<Mapper<ThumbnailDTO, String>>()
    private val mapper = CharacterMapper(mockThumbnailMapper)

    private val thumbnailUrl = "http://abc.jpg"

    @Before
    fun setUp() {
        stubThumbnailMapper()
    }

    private fun stubThumbnailMapper() {
        every { mockThumbnailMapper.map(any()) } returns thumbnailUrl
    }

    @Test
    fun `character mapping id`() {
        val characterDTO = CharacterDTOBuilder()
            .build()
        assertThat(mapper.map(characterDTO).id).isEqualTo(characterDTO.id)
    }

    @Test
    fun `character mapping name`() {
        val characterDTO = CharacterDTOBuilder()
            .build()
        assertThat(mapper.map(characterDTO).name).isEqualTo(characterDTO.name)
    }

    @Test
    fun `character mapping description`() {
        val characterDTO = CharacterDTOBuilder()
            .build()
        assertThat(mapper.map(characterDTO).description).isEqualTo(characterDTO.description)
    }

    @Test
    fun `character mapping thumbnail`() {
        val thumbnailUrlData = thumbnailUrl.split('.')
        val characterDTO = CharacterDTOBuilder()
            .setThumbnail(ThumbnailDTOBuilder().setPath(thumbnailUrlData[0]).setExtension(thumbnailUrlData[1]).build())
            .build()
        assertThat(mapper.map(characterDTO).thumbnail).isEqualTo(thumbnailUrl)
    }
}