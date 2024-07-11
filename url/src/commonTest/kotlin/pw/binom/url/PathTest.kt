package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class PathTest {
    @Test
    fun resolveTest() {
        assertEquals("a/b".toPath, "a".toPath.relative("b"))
        assertEquals("a/b".toPath, "a".toPath.relative("./b"))
        assertEquals("b".toPath, "a".toPath.relative("../b"))
        assertEquals("/b".toPath, "a".toPath.relative("/b"))
    }
}