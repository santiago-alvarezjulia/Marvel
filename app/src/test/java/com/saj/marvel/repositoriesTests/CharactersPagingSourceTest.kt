package com.saj.marvel.repositoriesTests

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.CharacterDTOBuilder
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.dtos.DataWrapperDTO
import com.saj.marvel.network.mappers.CharacterMapper
import com.saj.marvel.network.mappers.ListMapperImpl
import com.saj.marvel.network.mappers.ThumbnailMapper
import com.saj.marvel.repositories.CharactersPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersPagingSourceTest {

    private val mockMarvelWebService = mockk<MarvelWebService>()
    private val realListMapper = ListMapperImpl(
        CharacterMapper(
            ThumbnailMapper()
        )
    )

    @Test
    fun `returns page when successful load`() = runBlockingTest {
        val pagingSource = CharactersPagingSource(mockMarvelWebService, realListMapper)
        val character = CharacterDTOBuilder().build()
        stubWebServiceFetchCharacters(listOf(character))

        val expected = PagingSource.LoadResult.Page(
            data = realListMapper.map(listOf(character)),
            prevKey = 0,
            nextKey = 15
        )
        val actual = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    private fun stubWebServiceFetchCharacters(characters: List<CharacterDTO>) {
        val data = DataWrapperDTO(DataWrapperDTO.DataContainerDTO(0, 15, 1533, characters))
        coEvery { mockMarvelWebService.fetchPagedMarvelCharacters(any(), any()) } returns NetworkResponse.Success(data)
    }
}