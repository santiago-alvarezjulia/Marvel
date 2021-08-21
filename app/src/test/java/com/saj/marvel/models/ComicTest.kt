package com.saj.marvel.models

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ComicTest {

    @Test
    fun `comic with same id, is the same comic`() {
        val id = 1
        val name = "Comic"
        val comic = Comic(id, name)
        val comicSameId = Comic(id, name)
        assertThat(comic.isTheSame(comicSameId)).isTrue()
    }

    @Test
    fun `comic with diff id, is other comic`() {
        val name = "Comic"
        val event = Comic(1, name)
        val eventDiffId = Comic(2, name)
        assertThat(event.isTheSame(eventDiffId)).isFalse()
    }

    @Test
    fun `comic with same id but diff name have diff content`() {
        val id = 1
        val comic = Comic(id, "Name 1")
        val comicDiffName = Comic(id, "Name 2")
        assertThat(comic.isContentTheSame(comicDiffName)).isFalse()
    }
}