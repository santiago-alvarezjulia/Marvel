package com.saj.marvel.idlingResources

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoCountingIdlingResource {

    private const val RES_NAME = "EspressoCountingIdlingResource"

    val countingIdlingResource = lazy {
        CountingIdlingResource(RES_NAME)
    }

    fun processStarts() {
        countingIdlingResource.value.increment()
    }

    fun processEnds() {
        countingIdlingResource.value.decrement()
    }
}