package io.github.mohamedisoliman.kalley.network

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Mohamed Ibrahim on 2020-01-24.
 */
class RequestTest {
    @Test
    fun testDataClasses() {
        val r1 = Request("A")
        val r2 = Request("A")
        assertEquals(true, r1 == r2)
    }

}