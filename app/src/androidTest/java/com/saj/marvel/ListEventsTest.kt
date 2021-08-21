package com.saj.marvel

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.ui.fragments.EventsListFragment
import com.saj.marvel.utils.InstrumentedTestUtils.getMockResponse
import com.saj.marvel.utils.InstrumentedTestUtils.readJsonResponseAsString
import com.saj.marvel.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class ListEventsTest {

    companion object {
        private const val EXPECTED_ITEM_COUNT = 3
    }

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var mockWebServer : MockWebServer

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(EspressoCountingIdlingResource.countingIdlingResource.value)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoCountingIdlingResource.countingIdlingResource.value)
    }

    @Test
    fun eventsList() {
        launchFragmentInHiltContainer<EventsListFragment>()

        mockWebServer.enqueue(
            getMockResponse(
                readJsonResponseAsString("3-events-200.json"),
                200
            )
        )

        onView(ViewMatchers.withId(R.id.events_list)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            assertThat(recyclerView.adapter?.itemCount).isEqualTo(EXPECTED_ITEM_COUNT)
        }
    }
}