package com.saj.marvel.ui.models

import com.saj.marvel.models.Event

data class ListedEvent(private val event: Event, val isExpanded: Boolean = false)
