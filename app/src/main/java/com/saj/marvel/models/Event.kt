package com.saj.marvel.models

data class Event(val id: Int, val title: String, val startDate: String,
                 val thumbnail: String, val comics: List<Comic>)
