package com.saj.marvel.utils

import java.math.BigInteger
import java.security.MessageDigest

class MD5Digest : AuthDigest {

    companion object {
        private const val MD5 = "MD5"
    }

    override fun generateDigest(value: String) : String {
        val md = MessageDigest.getInstance(MD5)
        return BigInteger(1, md.digest(value.toByteArray())).toString(16)
            .padStart(32, '0')
    }
}