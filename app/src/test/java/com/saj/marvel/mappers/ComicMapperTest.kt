package com.saj.marvel.mappers

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.network.dtos.ComicListDTO
import com.saj.marvel.network.mappers.ComicMapper
import org.junit.Test

class ComicMapperTest {

    private val mapper = ComicMapper()

    @Test
    fun `map comic resourceUri to int id`() {
        val id = 12744
        val comicUri = "http://gateway.marvel.com/v1/public/comics/$id"
        val comicDto = ComicListDTO.ComicSummaryDTO(comicUri, "Thanos Event")
        assertThat(mapper.map(comicDto).id).isEqualTo(id)
    }
}