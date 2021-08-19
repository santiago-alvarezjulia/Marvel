package com.saj.marvel.models

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventBuilder
import org.junit.Test

class EventTest {

    @Test
    fun `event with same id, are the same event`() {
        val id = 1
        val event = EventBuilder().setId(id).setName("Thanos Event").build()
        val eventSameId = EventBuilder().setId(id).setName("Thanos Renamed Event").build()
        assertThat(event.isTheSame(eventSameId)).isTrue()
    }

    @Test
    fun `event with same id but diff name have diff content`() {
        val id = 1
        val event = EventBuilder().setId(id).setName("Thanos Event").build()
        val eventDiffName = EventBuilder().setId(id).setName("Thanos Renamed Event").build()
        assertThat(event.isContentTheSame(eventDiffName)).isFalse()
    }

    @Test
    fun `event with same id but diff startDate have diff content`() {
        val id = 1
        val name = "Thanos Event"
        val event = EventBuilder().setId(id).setName(name)
            .setStartDate("2020-01-01").build()
        val eventDiffStartDate = EventBuilder().setId(id).setName(name)
            .setStartDate("2020-01-02").build()
        assertThat(event.isContentTheSame(eventDiffStartDate)).isFalse()
    }

    @Test
    fun `event with same id but diff thumbnail have diff content`() {
        val id = 1
        val name = "Thanos Event"
        val newStartDate = "2020-01-01"
        val event = EventBuilder().setId(id)
            .setName(name).setStartDate(newStartDate).setThumbnail("url1").build()
        val eventDiffThumbnail = EventBuilder().setId(id)
            .setName(name).setStartDate(newStartDate).setThumbnail("url2").build()
        assertThat(event.isContentTheSame(eventDiffThumbnail)).isFalse()
    }
}