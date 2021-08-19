package com.saj.marvel.models

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterBuilder
import org.junit.Test

class CharacterTest {

    @Test
    fun `character with same id, are the same character`() {
        val id = 1
        val character = CharacterBuilder().setId(id).setName("Thanos").build()
        val characterSameId = CharacterBuilder().setId(id).setName("Thanos Renamed").build()
        assertThat(character.isTheSame(characterSameId)).isTrue()
    }

    @Test
    fun `character with same id but diff name have diff content`() {
        val id = 1
        val character = CharacterBuilder().setId(id).setName("Thanos").build()
        val characterDiffName = CharacterBuilder().setId(id).setName("Thanos Renamed").build()
        assertThat(character.isContentTheSame(characterDiffName)).isFalse()
    }

    @Test
    fun `character with same id but diff description have diff content`() {
        val id = 1
        val name = "Thanos"
        val character = CharacterBuilder().setId(id).setName(name)
            .setDescription("desc1").build()
        val characterDiffDescription = CharacterBuilder().setId(id).setName(name)
            .setDescription("desc2").build()
        assertThat(character.isContentTheSame(characterDiffDescription)).isFalse()
    }

    @Test
    fun `character with same id but diff thumbnail have diff content`() {
        val id = 1
        val name = "Thanos"
        val description = "description of thanos"
        val character = CharacterBuilder().setId(id)
            .setName(name).setDescription(description).setThumbnail("url1").build()
        val characterSameId = CharacterBuilder().setId(id)
            .setName(name).setDescription(description).setThumbnail("url2").build()
        assertThat(character.isContentTheSame(characterSameId)).isFalse()
    }
}