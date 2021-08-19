package com.saj.marvel.mappers

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.ThumbnailDTOBuilder
import com.saj.marvel.network.mappers.ThumbnailMapper
import org.junit.Test

class ThumbnailMapperTest {

    private val mapper = ThumbnailMapper()

    @Test
    fun `map thumbnailDto to string`() {
        val thumbnailUrl = "http://abc.jpg"
        val thumbnailUrlData = thumbnailUrl.split('.')
        val thumbnailDTO = ThumbnailDTOBuilder()
            .setPath(thumbnailUrlData[0])
            .setExtension(thumbnailUrlData[1])
            .build()
        assertThat(mapper.map(thumbnailDTO)).isEqualTo(thumbnailUrl)
    }
}