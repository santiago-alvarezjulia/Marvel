package com.saj.marvel

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth.assertThat
import com.saj.marvel.idlingResources.EspressoCountingIdlingResource
import com.saj.marvel.ui.adapters.CharactersAdapter
import com.saj.marvel.ui.fragments.CharactersListFragment
import com.saj.marvel.utils.InstrumentedTestUtils
import com.saj.marvel.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigateToCharacterDetailTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

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
    fun showCharacterDetailData() {
        launchCharactersListFragment()

        mockWebServer.enqueue(
            InstrumentedTestUtils.getMockResponse(
                InstrumentedTestUtils.readJsonResponseAsString("3-characters-200.json"),
                200
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.characters_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharactersAdapter.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.characterDetailFragment)
    }

    private fun launchCharactersListFragment() {
        launchFragmentInHiltContainer<CharactersListFragment> {
            navController.setGraph(R.navigation.nav_graph)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}