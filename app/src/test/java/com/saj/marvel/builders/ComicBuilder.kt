package com.saj.marvel.builders

import com.saj.marvel.models.Comic

class ComicBuilder {

    private var id = 12744
    private var name = "Alpha Flight (1983) #79"

    fun build() : Comic {
        return Comic(id, name)
    }
}