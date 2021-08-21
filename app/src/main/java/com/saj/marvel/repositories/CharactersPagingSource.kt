package com.saj.marvel.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Character
import com.saj.marvel.network.MarvelWebService
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.network.dtos.CharacterDTO
import com.saj.marvel.network.mappers.ListMapper
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val marvelWebService: MarvelWebService,
    private val charactersMapper: ListMapper<CharacterDTO, Character>
) : PagingSource<Int, Character>() {

    companion object {
        const val PAGE_SIZE = 15
        const val STARTING_OFFSET = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: STARTING_OFFSET
        EspressoCountingIdlingResource.processStarts()
        val response = marvelWebService.fetchPagedMarvelCharacters(PAGE_SIZE, offset)
        EspressoCountingIdlingResource.processEnds()
        return when(response) {
            is NetworkResponse.Success -> {
                val responseData = response.body.data
                val newOffset = if (responseData.total != responseData.offset)
                    responseData.offset + responseData.count
                else
                    null
                LoadResult.Page(
                    charactersMapper.map(
                        responseData.results
                    ),
                    params.key,
                    newOffset
                )
            }
            is NetworkResponse.ApiError -> LoadResult.Error(Exception(response.body.status))
            is NetworkResponse.NetworkError -> {
                LoadResult.Error(response.error)
            }
            is NetworkResponse.OtherError -> {
                response.error?.let {
                    LoadResult.Error(it)
                } ?: run {
                    LoadResult.Error(Exception())
                }
            }
        }
    }

}