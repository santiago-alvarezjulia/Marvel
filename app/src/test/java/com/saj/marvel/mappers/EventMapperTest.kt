package com.saj.marvel.mappers

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventDTOBuilder
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.mappers.ComicMapper
import com.saj.marvel.network.mappers.EventMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.ThumbnailMapper
import org.junit.Test

class EventMapperTest {

    private val realThumbnailMapper = ThumbnailMapper()
    private val realComicListMapper = ListMapperImpl(ComicMapper())
    private val mapper = EventMapper(realThumbnailMapper, realComicListMapper)

    @Test
    fun `event mapping id`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).id).isEqualTo(eventDTO.id)
    }

    @Test
    fun `event mapping title`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).title).isEqualTo(eventDTO.title)
    }

    @Test
    fun `event mapping startDate`() {
        val eventDTO = EventDTOBuilder()
            .build()
        assertThat(mapper.map(eventDTO).startDate).isEqualTo(eventDTO.startDate)
    }

    @Test
    fun `event mapping null startDate to empty string`() {
        val eventDTO = EventDTOBuilder()
            .setStartDate(null)
            .build()
        assertThat(mapper.map(eventDTO).startDate).isEqualTo("")
    }

    @Test
    fun `event mapping thumbnail`() {
        val thumbnailUrl = "http://abc.jpg"
        val thumbnailUrlData = thumbnailUrl.split('.')
        val eventDTO = EventDTOBuilder()
            .setThumbnail(ThumbnailDTOBuilder().setPath(thumbnailUrlData[0]).setExtension(thumbnailUrlData[1]).build())
            .build()
        assertThat(mapper.map(eventDTO).thumbnail).isEqualTo(thumbnailUrl)
    }

    @Test
    fun `event mapping comic`() {
        val comicId = 10
        val comicName = "Thanos Comic"
        val comics = ComicListDTO(listOf(ComicListDTO.ComicSummaryDTO("uri/$comicId", comicName)))
        val eventDTO = EventDTOBuilder()
            .setNewComics(comics)
            .build()
        assertThat(mapper.map(eventDTO).comics[0].name).isEqualTo(comicName)
        assertThat(mapper.map(eventDTO).comics[0].id).isEqualTo(comicId)
    }
}