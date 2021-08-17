package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.mappers.CharacterMapper
import org.junit.Test

class CharacterMapperTest {

    private val mapper = CharacterMapper()

    @Test
    fun `map thumbnailDto to string`() {
        val characterDTO = CharacterDTOBuilder()
            .setThumbnail(ThumbnailDTOBuilder().setPath("http://abc").setExtension("jpg").build())
            .build()
        assertThat(mapper.map(characterDTO).thumbnail).isEqualTo("http://abc.jpg")
    }
}