package com.saj.marvel.builders

import com.saj.marvel.network.dtos.ThumbnailDTO

class ThumbnailDTOBuilder {

    private var path = "http://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd"
    private var extension = "jpg"

    fun setPath(newPath: String) : ThumbnailDTOBuilder {
        this.path = newPath
        return this
    }

    fun setExtension(newExtension: String) : ThumbnailDTOBuilder {
        this.extension = newExtension
        return this
    }

    fun build() : ThumbnailDTO {
        return ThumbnailDTO(path, extension)
    }
}