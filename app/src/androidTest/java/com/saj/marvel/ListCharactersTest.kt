package com.saj.marvel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.saj.marvel.ui.HomeActivity
import com.saj.marvel.utils.RecyclerViewItemCounterAssertion
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@Ignore("Code is missing to make it pass")
class ListCharactersTest {

    companion object {
        private const val EXP_ITEM_COUNT = 3
    }

    @get:Rule
    val homeActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun charactersList() {
        onView(withId(R.id.characters_list)).check(RecyclerViewItemCounterAssertion(EXP_ITEM_COUNT))
    }
}