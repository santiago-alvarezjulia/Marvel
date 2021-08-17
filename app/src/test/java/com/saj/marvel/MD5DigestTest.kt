package com.saj.marvel

import com.google.common.truth.Truth.assertThat
import com.saj.marvel.utils.MD5Digest
import org.junit.Test

class MD5DigestTest {

    private val mD5Digest = MD5Digest()

    @Test
    fun `given a string and it md5 digest, MD5Digest generates correct digest`() {
        val value = "Welcome"
        val md5 = "83218ac34c1834c26781fe4bde918ee4"
        assertThat(md5).isEqualTo(mD5Digest.generateDigest(value))
    }
}