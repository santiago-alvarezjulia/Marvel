package com.saj.marvel.viewModelsTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.builders.EventBuilder
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.models.Event
import com.saj.marvel.network.NetworkResponse
import com.saj.marvel.repositories.EventsRepositoryInt
import com.saj.marvel.utils.MainCoroutineRule
import com.saj.marvel.viewModels.EventsViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    private val mockEventsRepo = mockk<EventsRepositoryInt>()
    private val mockSavedStateHandle = mockk<SavedStateHandle>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        mockkObject(EspressoCountingIdlingResource)
        every { EspressoCountingIdlingResource.processStarts() } returns Unit
        every { EspressoCountingIdlingResource.processEnds() } returns Unit

        every { mockSavedStateHandle.set<List<Event>>(any(), any()) } returns Unit
    }

    @Test
    fun `getMarvelEvents return empty list when no events`() {
        stubRepoFetchEvents(emptyList())
        stubSavedStateHandleGet(emptyList())
        val viewModel = EventsViewModel(mockSavedStateHandle, mockEventsRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.eventsLiveData.value).isEmpty()
    }

    @Test
    fun `getMarvelEvents return list of available events`() {
        val events = listOf(EventBuilder().build())
        stubRepoFetchEvents(events)
        stubSavedStateHandleGet(emptyList())
        val viewModel = EventsViewModel(mockSavedStateHandle, mockEventsRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.eventsLiveData.value).hasSize(events.size)
    }

    @Test
    fun `saved state handle events return instead of repo getMarvelEvents`() {
        val events = listOf(EventBuilder().build())
        stubRepoFetchEvents(emptyList())
        stubSavedStateHandleGet(events)
        val viewModel = EventsViewModel(mockSavedStateHandle, mockEventsRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.eventsLiveData.value).hasSize(events.size)
    }

    @Test
    fun `getMarvelEvents return list of available listed events`() {
        val events = listOf(EventBuilder().build())
        stubRepoFetchEvents(events)
        stubSavedStateHandleGet(emptyList())
        val viewModel = EventsViewModel(mockSavedStateHandle, mockEventsRepo,
            coroutineRule.testDispatcher)
        assertThat(viewModel.listedEventsLiveData.value?.get(0)?.isExpanded).isFalse()
    }

    @Test
    fun `when network response is error, post live data event`() {
        stubFetchEventsReturnsError()
        stubSavedStateHandleGet(emptyList())
        val viewModel = EventsViewModel(mockSavedStateHandle, mockEventsRepo,
            coroutineRule.testDispatcher)

        assertThat(viewModel.loadEventsErrorLiveData.value?.peekContent()).isNotNull()
    }

    private fun stubFetchEventsReturnsError() {
        coEvery { mockEventsRepo.fetchMarvelEvents() } returns NetworkResponse.OtherError(null)
    }

    private fun stubRepoFetchEvents(events: List<Event>) {
        coEvery { mockEventsRepo.fetchMarvelEvents() } returns NetworkResponse.Success(events)
    }

    private fun stubSavedStateHandleGet(events: List<Event>) {
        every { mockSavedStateHandle.get<List<Event>>(any()) } returns events
    }
}