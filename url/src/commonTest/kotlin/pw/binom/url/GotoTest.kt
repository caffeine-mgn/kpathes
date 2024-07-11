package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class GotoTest {
    @Test
    fun otherWithoutSchema() {
        val result = "https://google.com".toURL().goto("://test.com/123".toURI())
        assertEquals("https://test.com/123".toURL(), result)
    }

    @Test
    fun selfRelativeAbsolute() {
        val result = "https://google.com/aa/bb".toURL().goto("/aaa".toURI())
        assertEquals("https://google.com/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeParent() {
        val result = "https://google.com/aa/bb".toURL().goto("../aaa".toURI())
        assertEquals("https://google.com/aa/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeCurrent() {
        val result = "https://google.com/aa/bb".toURL().goto("./aaa".toURI())
        assertEquals("https://google.com/aa/bb/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeReplaceCurrent() {
        val result = "https://google.com/aa/bb".toURL().goto("aaa".toURI())
        assertEquals("https://google.com/aa/aaa".toURL(), result)
    }
}