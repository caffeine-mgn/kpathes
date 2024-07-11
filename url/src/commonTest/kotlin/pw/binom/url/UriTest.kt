package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class UriTest {
    @Test
    fun withoutSchemaTest() {
        assertEquals("google.com".toPath, "://google.com".toURI().path)
        assertEquals("google.com/".toPath, "://google.com/".toURI().path)
        assertEquals("google.com/test".toPath, "://google.com/test".toURI().path)
        assertEquals("google.com/test".toPath, "://google.com/test?a=b".toURI().path)
        assertEquals("google.com/test".toPath, "://google.com/test?a=b#aaa".toURI().path)
        assertEquals("google.com/test".toPath, "://google.com/test#aaa".toURI().path)
    }
}