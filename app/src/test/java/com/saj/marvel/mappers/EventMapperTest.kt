package com.saj.marvel.mappers

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventDTOBuilder
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.dtos.ThumbnailDTO
import com.saj.marvel.network.mappers.EventMapper
import com.saj.marvel.network.mappers.Mapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class EventMapperTest {

    private val mockThumbnailMapper = mockk<Mapper<ThumbnailDTO, String>>()
    private val mapper = EventMapper(mockThumbnailMapper)

    private val thumbnailUrl = "http://abc.jpg"

    @Before
    fun setUp() {
        stubThumbnailMapper()
    }

    private fun stubThumbnailMapper() {
        every { mockThumbnailMapper.map(any()) } returns thumbnailUrl
    }

    @Test
    fun `event mapping id`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).id).isEqualTo(eventDTO.id)
    }

    @Test
    fun `event mapping name`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).name).isEqualTo(eventDTO.name)
    }

    @Test
    fun `event mapping startDate`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).startDate).isEqualTo(eventDTO.startDate)
    }

    @Test
    fun `event mapping thumbnail`() {
        val thumbnailUrlData = thumbnailUrl.split('.')
        val eventDTO = EventDTOBuilder()
            .setThumbnail(ThumbnailDTOBuilder().setPath(thumbnailUrlData[0]).setExtension(thumbnailUrlData[1]).build())
            .build()
        assertThat(mapper.map(eventDTO).thumbnail).isEqualTo(thumbnailUrl)
    }
}