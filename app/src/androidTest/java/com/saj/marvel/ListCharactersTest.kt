package com.saj.marvel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.ui.fragments.CharactersListFragment
import com.saj.marvel.utils.InstrumentedTestUtils.getMockResponse
import com.saj.marvel.utils.InstrumentedTestUtils.readJsonResponseAsString
import com.saj.marvel.utils.RecyclerViewItemCounterAssertion
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
class ListCharactersTest {

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
    fun charactersList() {
        launchFragmentInHiltContainer<CharactersListFragment>()

        mockWebServer.enqueue(
            getMockResponse(
                readJsonResponseAsString("3-characters-200.json"),
                200
            )
        )

        onView(withId(R.id.characters_list))
            .check(RecyclerViewItemCounterAssertion(EXPECTED_ITEM_COUNT))
    }
}