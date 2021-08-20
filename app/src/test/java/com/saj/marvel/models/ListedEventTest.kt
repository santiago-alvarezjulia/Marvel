package com.saj.marvel.models

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventBuilder
import com.saj.marvel.ui.models.ListedEvent
import org.junit.Test

class ListedEventTest {

    @Test
    fun `event with same id, are the same event`() {
        val id = 1
        val event = ListedEvent(EventBuilder().setId(id).setName("Thanos Event").build())
        val eventSameId = ListedEvent(EventBuilder().setId(id).setName("Thanos Renamed Event").build())
        assertThat(event.isTheSame(eventSameId)).isTrue()
    }

    @Test
    fun `event with same id but diff name have diff content`() {
        val id = 1
        val event = ListedEvent(EventBuilder().setId(id).setName("Thanos Event").build())
        val eventDiffName = ListedEvent(EventBuilder().setId(id).setName("Thanos Renamed Event").build())
        assertThat(event.isContentTheSame(eventDiffName)).isFalse()
    }

    @Test
    fun `event with same id but diff startDate have diff content`() {
        val id = 1
        val name = "Thanos Event"
        val event = ListedEvent(EventBuilder().setId(id).setName(name)
            .setStartDate("2020-01-01").build())
        val eventDiffStartDate = ListedEvent(EventBuilder().setId(id).setName(name)
            .setStartDate("2020-01-02").build())
        assertThat(event.isContentTheSame(eventDiffStartDate)).isFalse()
    }

    @Test
    fun `event with same id but diff thumbnail have diff content`() {
        val id = 1
        val name = "Thanos Event"
        val newStartDate = "2020-01-01"
        val event = ListedEvent(EventBuilder().setId(id)
            .setName(name).setStartDate(newStartDate).setThumbnail("url1").build())
        val eventDiffThumbnail = ListedEvent(EventBuilder().setId(id)
            .setName(name).setStartDate(newStartDate).setThumbnail("url2").build())
        assertThat(event.isContentTheSame(eventDiffThumbnail)).isFalse()
    }
}