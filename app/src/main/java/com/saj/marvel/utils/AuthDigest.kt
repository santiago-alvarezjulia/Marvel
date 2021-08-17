package com.saj.marvel.utils

interface AuthDigest {
    fun generateDigest(value: String) : String
}