package com.example.core.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CollectionExtTest {

    @Test
    fun `test swap extension`() {
        val a = mutableListOf(1, 2, 3)
        a.swap(1, 2)
        assertThat(a).isEqualTo(mutableListOf(1, 3, 2))
    }
}
