package pw.binom.url

import kotlin.test.*

class HostPortTest {
    @Test
    fun hostAndPortTest() {
        HostPort("google.com:80").also {
            assertEquals("google.com", it.domain)
            assertEquals(80, it.port)
        }
    }

    @Test
    fun hostOnlyTest() {
        HostPort("google.com").also {
            assertEquals("google.com", it.domain)
            assertNull(it.port)
        }
    }

    @Test
    fun emptyHostTest() {
        assertTrue(assertFails { HostPort(":80") } is IllegalArgumentException)
    }

    @Test
    fun invalidPortTest() {
        assertTrue(assertFails { HostPort("google.com:port") } is IllegalArgumentException)
    }

    @Test
    fun severalPortsTest() {
        assertTrue(assertFails { HostPort("google.com:1:2") } is IllegalArgumentException)
    }

    @Test
    fun constructorTest() {
        HostPort(domain = "google.com", port = 80).also {
            assertEquals("google.com", it.domain)
            assertEquals(80, it.port)
        }
    }
}