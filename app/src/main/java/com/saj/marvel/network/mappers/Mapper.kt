package com.saj.marvel.network.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}