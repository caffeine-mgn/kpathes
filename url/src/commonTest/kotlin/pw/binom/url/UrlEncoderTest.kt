package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class UrlEncoderTest {

    @Test
    fun testEncode() {
        assertEquals(
            "http%3A%2F%2Fexample.com%2Ftest%3Fff%3Dffff",
            UrlEncoder.encode("http://example.com/test?ff=ffff")
        )
    }
}