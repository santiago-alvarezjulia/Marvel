package com.saj.marvel.network.mappers

import com.saj.marvel.network.dtos.ThumbnailDTO
import javax.inject.Inject

class ThumbnailMapper @Inject constructor() : Mapper<ThumbnailDTO, String> {
    override fun map(input: ThumbnailDTO): String {
        return input.path.plus('.').plus(input.extension)
    }
}