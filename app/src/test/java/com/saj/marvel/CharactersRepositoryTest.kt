package com.saj.marvel

import com.google.common.truth.Truth
import com.saj.marvel.repositories.CharactersRepository
import org.junit.Test

class CharactersRepositoryTest {

    @Test
    fun `fetchMarvelCharacters return empty list when no characters`() {
        val repo = CharactersRepository()
        Truth.assertThat(repo.fetchMarvelCharacters()).isEmpty()
    }
}